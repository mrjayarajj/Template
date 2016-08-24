package com.baseframework.web.filters;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.DispatcherType;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.constructs.blocking.LockTimeoutException;
import net.sf.ehcache.constructs.web.AlreadyCommittedException;
import net.sf.ehcache.constructs.web.AlreadyGzippedException;
import net.sf.ehcache.constructs.web.filter.FilterNonReentrantException;
import net.sf.ehcache.constructs.web.filter.SimplePageFragmentCachingFilter;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class CacheFilter extends SimplePageFragmentCachingFilter {

	private FilterConfig filterConfig = null;

	@Override
	public void doInit(FilterConfig filterConfig) throws CacheException {
		this.filterConfig = filterConfig;
		super.doInit(filterConfig);
	}

	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws AlreadyGzippedException, AlreadyCommittedException,
			FilterNonReentrantException, LockTimeoutException, Exception {

		String qs = request.getQueryString();
		DispatcherType dt = request.getDispatcherType();
		Map m = request.getParameterMap();
		String sa[] = m.get("cache") != null ? (String[]) m.get("cache") : null;
		boolean cache = sa != null && sa.length > 0 && sa[0].equals("INCLUDE");

		if (dt.equals(DispatcherType.REQUEST) && (qs != null && qs.contains("cache=REQUEST"))) {
			super.doFilter(request, response, chain);
		} else if (dt.equals(DispatcherType.FORWARD) && (qs != null && qs.contains("cache=FORWARD"))) {
			super.doFilter(request, response, chain);
		} else if (dt.equals(DispatcherType.INCLUDE) && cache) {
			super.doFilter(request, response, chain);
		} else {
			chain.doFilter(request, response);
		}
	}

	@Override
	protected String calculateKey(HttpServletRequest httpRequest) {
		
		StringBuffer sb = new StringBuffer();
		DispatcherType dt = httpRequest.getDispatcherType();
		if (dt.equals(DispatcherType.INCLUDE)) {
			sb.append(httpRequest.getAttribute("javax.servlet.include.servlet_path"));
			Map m = httpRequest.getParameterMap();
			Set<Map.Entry<String, String[]>> mes = m.entrySet();
			Iterator<Map.Entry<String, String[]>> itr = mes.iterator();
			sb.append("?");
			for (int i=0 ;itr.hasNext();i++) {
				Map.Entry<String, String[]> me = itr.next();
				
				if(i>0)
					sb.append("&");
					
				if(me.getKey().equals("cache")){
					sb.append(me.getKey());
					sb.append("=");
					String sa[] = me.getValue();
					sb.append(sa[0]);					
				}else{				
					sb.append(me.getKey());
					sb.append("=");
					for(String s : me.getValue()){
						sb.append(s);
						sb.append(",");
					}					
				}
				sb.append("&");
			}
		}else{
			String qs = httpRequest.getQueryString();
			if(qs!=null){
				sb.append(httpRequest.getRequestURI()).append("?").append(httpRequest.getQueryString());
			} else{
				sb.append(httpRequest.getRequestURI());
			}
		}

		return sb.toString();
	}

	@Override
	protected CacheManager getCacheManager() {
		WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(filterConfig.getServletContext());
		CacheManager cm = (CacheManager) webApplicationContext.getBean("cacheManager");
		return cm;
	}

	@Override
	protected boolean acceptsGzipEncoding(HttpServletRequest request) {
		return false;
	}
}

	