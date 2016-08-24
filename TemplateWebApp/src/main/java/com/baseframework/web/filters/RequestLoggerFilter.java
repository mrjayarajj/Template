package com.baseframework.web.filters;

import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.baseframework.domain.security.access.Function;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * A filter that logs each request.
 */
public class RequestLoggerFilter implements Filter {

	private static Logger LOG = LoggerFactory.getLogger(RequestLoggerFilter.class);

	private static Logger LOG_LOGPERREQ = LoggerFactory.getLogger(RequestLoggerFilter.class.getName() + "$LogPerReq");
	private static Logger LOG_INCLUDE = LoggerFactory.getLogger(RequestLoggerFilter.class.getName() + "$DispatcherType.INCLUDE");
	private static Logger LOG_FORWARD = LoggerFactory.getLogger(RequestLoggerFilter.class.getName() + "$DispatcherType.FORWARD");

	public void init(final FilterConfig filterConfig) throws ServletException {

	}

	public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse, final FilterChain filterChain) throws IOException, ServletException {

		final long startTime = System.currentTimeMillis();

		if (servletRequest instanceof HttpServletRequest) {

			HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

			HttpServletRequest wrappedHttpServletRequest = new RequestWrapper((HttpServletRequest) servletRequest);
			HttpServletResponse wrappedHttpServletResponse = new ResponseWrapper((HttpServletResponse) servletResponse);

			String reqId = configureLogInfo(httpServletRequest);
			enableLogPerRequest(httpServletRequest);

			final String requestDetails = buildRequestDetails((RequestWrapper) wrappedHttpServletRequest);

			// for internalization
			wrappedHttpServletResponse.setCharacterEncoding("UTF-8");
			wrappedHttpServletRequest.setCharacterEncoding("UTF-8");

			// for browser cache
			// wrappedHttpServletResponse.setHeader("Cache-Control", "public,
			// cache, no-store, must-revalidate,max-age=300000"); // HTTP 1.1.
			// "private, no-cache, no-store, must-revalidate, max-age=0"
			// wrappedHttpServletResponse.setHeader("Pragma", "cache"); // HTTP
			// 1.0. "no-cache"
			// wrappedHttpServletResponse.setDateHeader("Expires",
			// System.currentTimeMillis() + 604800000L ); // Proxies. "0"

			try {

				filterChain.doFilter(wrappedHttpServletRequest, wrappedHttpServletResponse);

			} catch (Exception e) {

				StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter(sw);
				e.printStackTrace(pw);
				wrappedHttpServletRequest.setAttribute("EXCEPTION", "#" + reqId + "# " + sw.toString());

				LOG.error(e.getMessage(), e);

				throw e;

			} finally {

				final long endTime = System.currentTimeMillis();
				final long duration = endTime - startTime;

				int status = wrappedHttpServletResponse.getStatus();
				DispatcherType dispatcherType = httpServletRequest.getDispatcherType();

				if (LOG.isDebugEnabled()) {
					if (dispatcherType.equals(DispatcherType.INCLUDE) && LOG_INCLUDE.isDebugEnabled() == false) {
						// skip
					} else if (dispatcherType.equals(DispatcherType.FORWARD) && LOG_FORWARD.isDebugEnabled() == false) {
						// skip
					} else {
						LOG.debug(requestDetails + " (" + duration + ") ms (status:" + status + ")");
					}
				}

				disableLogPerRequest();
				deConfigureLogInfo();
			}

			// once all the filter is done stop
			return;
		}

		// if the request is not an servlet request
		filterChain.doFilter(servletRequest, servletResponse);
	}

	/**
	 * Configure log properties for log4j1 (org.apache.log4j.MDC) and log4j2
	 * (org.slf4j.MDC)
	 * 
	 * @param httpServletRequest
	 * @return uniqueId to show in 500 error page
	 */
	private String configureLogInfo(HttpServletRequest httpServletRequest) {

		StringBuffer sbf = new StringBuffer();

		String id = UUID.randomUUID().toString();
		sbf.append(id);
		sbf.append("-");
		sbf.append(System.currentTimeMillis());

		org.slf4j.MDC.put("requestId", id + "-" + System.currentTimeMillis());
		org.apache.log4j.MDC.put("requestId", id + "-" + System.currentTimeMillis());

		org.slf4j.MDC.put("ipAddress", httpServletRequest.getRemoteAddr());
		org.apache.log4j.MDC.put("ipAddress", httpServletRequest.getRemoteAddr());
		sbf.append("|");
		sbf.append(httpServletRequest.getRemoteAddr());

		HttpSession session = httpServletRequest.getSession(false);

		if (session != null) {
			org.slf4j.MDC.put("sessionId", session.getId());
			org.apache.log4j.MDC.put("sessionId", session.getId());

			sbf.append("|");
			sbf.append(session.getId());

			UserDetails ud = getUserDetails(httpServletRequest);
			if (ud != null) {
				org.slf4j.MDC.put("userId", ud.toString());
				org.apache.log4j.MDC.put("userId", ud.toString());

				sbf.append("|");
				sbf.append(ud.toString());
			}
		}
		org.slf4j.MDC.put("hostName", httpServletRequest.getServerName());
		org.apache.log4j.MDC.put("hostName", httpServletRequest.getServerName());

		sbf.append("|");
		sbf.append(httpServletRequest.getServerName());

		return sbf.toString();
	}

	private void deConfigureLogInfo() {

		org.apache.log4j.MDC.remove("requestId");
		org.apache.log4j.MDC.remove("ipAddress");
		org.apache.log4j.MDC.remove("sessionId");
		org.apache.log4j.MDC.remove("userId");
		org.apache.log4j.MDC.remove("hostName");

		// org.slf4j.MDC.remove("requestId");
		// org.slf4j.MDC.remove("ipAddress");
		// org.slf4j.MDC.remove("sessionId");
		// org.slf4j.MDC.remove("userId");
		// org.slf4j.MDC.remove("hostName");

	}

	private UserDetails getUserDetails(HttpServletRequest httpServletRequest) {
		HttpSession session = httpServletRequest.getSession(false);

		if (session != null) {
			SecurityContext securityContext = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");
			if (securityContext != null && securityContext.getAuthentication() != null) {
				Authentication authentication = securityContext.getAuthentication();
				Object principal = authentication.getPrincipal();
				UserDetails ud = principal instanceof UserDetails ? (UserDetails) principal : null;
				return ud;
			}
		}

		return null;
	}

	static class SimpleTest extends Mockito {

		public static void main_(String[] args) {
			List<Integer> li = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
			System.out.println(li.stream().filter(e -> e.equals(22)).findFirst());
		}

		public static void main(String[] args) throws IOException, ServletException {
			HttpServletRequest req = mock(HttpServletRequest.class);
			HttpSession ses = mock(HttpSession.class);
			SecurityContext sc = mock(SecurityContext.class);
			Authentication auth = mock(Authentication.class);

			Collection grants = Arrays.asList(new Function(1, "BF_CONTROL_LOG_PER_REQ"));

			when(req.getRemoteAddr()).thenReturn("10.1.2.34");
			when(req.getServerName()).thenReturn("TemplateApp234");
			when(req.getDispatcherType()).thenReturn(DispatcherType.REQUEST);
			when(req.getSession()).thenReturn(ses);

			when(ses.getAttribute("SPRING_SECURITY_CONTEXT")).thenReturn(sc);

			when(sc.getAuthentication()).thenReturn(auth);
			when(auth.getAuthorities()).thenReturn(null);

			HttpServletResponse res = mock(HttpServletResponse.class);

			FilterChain filterChain = mock(FilterChain.class);

			new RequestLoggerFilter().doFilter(req, res, filterChain);
		}
	}

	private void enableLogPerRequest(HttpServletRequest httpServletRequest) {

		if (LOG_LOGPERREQ.isDebugEnabled() == false) {
			return;
		}

		boolean isGranted =

		Optional.of(httpServletRequest).map(req -> req.getSession()).map(ses -> (SecurityContext) ses.getAttribute("SPRING_SECURITY_CONTEXT")).map(sec -> sec.getAuthentication()).map(auth -> auth.getAuthorities()).map(grants -> grants.stream().filter(gAuth -> gAuth.getAuthority().equals("BF_CONTROL_LOG_PER_REQ")).findFirst())
				.map(e -> e.isPresent() ? e.get() : null).isPresent();

		if (isGranted == false) {
			return;
		}

		Cookie[] cookies = httpServletRequest.getCookies();

		if (cookies == null) {
			return;
		}

		Cookie cookie = null;

		for (int i = 0; i < cookies.length; i++) {
			if (cookies[i].getName().equals("x-debug-enabled")) {
				cookie = cookies[i];
				org.slf4j.MDC.put("x-debug-enabled", cookie.getValue());
				break;
			}
		}

		if (cookie == null) {
			return;
		}

		String logKeyValues[] = cookie.getValue().split("#");

		for (int i = 0; i < logKeyValues.length; i++) {
			String logKeyValue[] = logKeyValues[i].split("-");
			org.slf4j.MDC.put(logKeyValue[0], logKeyValue[1]);
		}
	}

	private void disableLogPerRequest() {

		String cookie = org.slf4j.MDC.get("x-debug-enabled");

		if (cookie == null) {
			return;
		}

		String logKeyValues[] = cookie.split(",");

		for (int i = 0; i < logKeyValues.length; i++) {
			String logKeyValue[] = logKeyValues[i].split("-");
			org.slf4j.MDC.remove(logKeyValue[0]);
		}

		org.slf4j.MDC.remove("x-debug-enabled");

	}

	public void destroy() {
	}

	protected String buildRequestDetails(final RequestWrapper request) {
		String queryString = request.getQueryString();

		String child = (String) request.getAttribute("javax.servlet.include.servlet_path");

		final String requestUri = request.getRequestURI();
		final DispatcherType dispatcherType = request.getDispatcherType();

		final String securePrefix = request.isSecure() ? "https://" : "http://";
		final String methodPrefix = request.getMethod();

		final String cookies = request.getCookiesAsString();

		final String headers = request.getHeaderAsString();

		StringBuffer sbf = new StringBuffer();
		sbf.append(dispatcherType);
		sbf.append(" ");
		sbf.append(methodPrefix);
		sbf.append(" ");
		sbf.append(securePrefix);
		sbf.append(request.getServerName());
		sbf.append(":");
		sbf.append(request.getServerPort());
		sbf.append(requestUri);
		sbf.append(queryString == null ? " " : "?" + queryString);
		sbf.append(child == null ? "" : " > " + child);
		sbf.append(cookies == null ? "" : " COOKIES[ " + cookies + "]");
		sbf.append(headers == null ? "" : " HEADERS[ " + headers + "]");

		return sbf.toString();
	}

	/**
	 * SessionProxy proxy class
	 * @author jjaganat
	 *
	 */
	protected static class SessionProxy implements HttpSession {

		private static final Logger LOG = LoggerFactory.getLogger(SessionProxy.class);

		private HttpSession targetHttpSession = null;

		public SessionProxy(HttpSession targetHttpSession) {
			this.targetHttpSession = targetHttpSession;
		}

		public Enumeration<String> getAttributeNames() {
			return targetHttpSession.getAttributeNames();
		}

		public long getCreationTime() {
			return targetHttpSession.getCreationTime();
		}

		public String getId() {
			return targetHttpSession.getId();
		}

		public long getLastAccessedTime() {
			return targetHttpSession.getLastAccessedTime();
		}

		public int getMaxInactiveInterval() {
			return targetHttpSession.getMaxInactiveInterval();
		}

		public ServletContext getServletContext() {
			return targetHttpSession.getServletContext();
		}

		public HttpSessionContext getSessionContext() {
			return targetHttpSession.getSessionContext();
		}

		public Object getValue(String arg0) {
			return targetHttpSession.getValue(arg0);
		}

		public String[] getValueNames() {
			return targetHttpSession.getValueNames();
		}

		public void invalidate() {
			targetHttpSession.invalidate();
		}

		public boolean isNew() {
			return targetHttpSession.isNew();
		}

		public void putValue(String arg0, Object arg1) {
			targetHttpSession.putValue(arg0, arg1);
		}

		public void removeAttribute(String arg0) {
			targetHttpSession.removeAttribute(arg0);
		}

		public void removeValue(String arg0) {
			targetHttpSession.removeValue(arg0);
		}

		public void setAttribute(String arg0, Object arg1) {
			targetHttpSession.setAttribute(arg0, arg1);
		}

		public Object getAttribute(String arg0) {
			return targetHttpSession.getAttribute(arg0);
		}

		public void setAttribute_(String arg0, Object arg1) {
			WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
			CacheManager cm = (CacheManager) webApplicationContext.getBean("cacheManager");
			Cache c = cm.getCache("SESSION_CACHE");
			c.put(new Element(getId() + "#" + arg0, arg1));
		}

		public Object getAttribute_(String arg0) {
			WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
			CacheManager cm = (CacheManager) webApplicationContext.getBean("cacheManager");
			Cache c = cm.getCache("SESSION_CACHE");
			Element e = (Element) c.get(getId() + "#" + arg0);
			return e != null ? e.getObjectValue() : null;
		}

		public void setMaxInactiveInterval(int arg0) {
			targetHttpSession.setMaxInactiveInterval(arg0);
		}

	}

	/**
	 * RequestWrapper adapter pattern 
	 * @author jjaganat
	 *
	 */
	protected static class RequestWrapper extends HttpServletRequestWrapper {

		private static Logger LOG_COOKIE = LoggerFactory.getLogger(RequestLoggerFilter.class.getName() + "$Cookie");
		private static Logger LOG_HEADER = LoggerFactory.getLogger(RequestLoggerFilter.class.getName() + "$Header");

		public RequestWrapper(HttpServletRequest request) {
			super(request);
		}

		public HttpSession getSession(boolean create) {
			HttpSession ses = super.getSession(create);
			if (ses != null)
				return new SessionProxy(ses);
			else
				return ses;
		}

		public HttpSession getSession() {
			HttpSession ses = super.getSession();
			if (ses != null)
				return new SessionProxy(ses);
			else
				return ses;
		}

		public String getCookiesAsString() {

			if (LOG_COOKIE.isDebugEnabled() == false) {
				return null;
			}

			StringBuffer cookiesData = new StringBuffer();

			final Cookie[] cookies = getCookies();
			if (cookies != null) {
				for (final Cookie cookie : cookies) {
					cookiesData.append(cookie.getName()).append("=").append(cookie.getValue()).append(",").append(cookie.getDomain());
					cookiesData.append("<br>");
				}
			}

			return cookiesData.toString();

		}

		public String getHeaderAsString() {
			StringBuffer headerData = new StringBuffer();

			Enumeration headerNames = getHeaderNames();

			if (headerNames != null) {
				while (headerNames.hasMoreElements()) {
					String headerName = (String) headerNames.nextElement();
					headerData.append(headerName).append("=").append(getHeader(headerName));
					headerData.append(",");
				}
			}

			if (LOG_HEADER.isDebugEnabled()) {
				return headerData.toString();
			} else {
				return null;
			}
		}

	}

	protected static class ResponseWrapper extends HttpServletResponseWrapper {

		private int status = HttpURLConnection.HTTP_OK;

		public ResponseWrapper(final HttpServletResponse response) {
			super(response);
		}

		public void setStatus(final int status) {
			super.setStatus(status);
			this.status = status;
		}

		public int getStatus() {
			return status;
		}

		public void sendError(final int status, final String msg) throws IOException {
			super.sendError(status, msg);
			this.status = status;
		}

		public void sendError(final int status) throws IOException {
			super.sendError(status);
			this.status = status;
		}

		public ServletOutputStream getOutputStream() throws IOException {
			return super.getOutputStream();
		}

	}
}
