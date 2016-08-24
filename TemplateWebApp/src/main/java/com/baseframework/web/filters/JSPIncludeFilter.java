package com.baseframework.web.filters;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JSPIncludeFilter implements Filter {

	private static final Logger LOG = LoggerFactory.getLogger(JSPIncludeFilter.class);

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		String reqUri = (String) httpRequest.getAttribute("javax.servlet.forward.request_uri");

		StringBuffer path = new StringBuffer();

		if (reqUri != null) {
			path.append("[ " + reqUri + " ]");
		}

		path.append(" P=>").append(httpRequest.getRequestURI());

		String child = (String) httpRequest.getAttribute("javax.servlet.include.servlet_path");

		if (child != null) {
			path.append(" C=>").append(child);
		}

		PrintWriter out = httpResponse.getWriter();
		Long st = System.currentTimeMillis();

		if (LOG.isDebugEnabled()) {
			out.write("<!-- JSP INCLUDE START :" + path + " at " + new Date() + "-->");
		}
		chain.doFilter(request, response);
		Long end = System.currentTimeMillis();
		if (LOG.isDebugEnabled()) {
			out.write("<!-- JSP INCLUDE END :" + path + " took (" + (end - st) + ") ms -->");
		}
	}

	public void init(FilterConfig filterConfig) throws ServletException {
	}
}
