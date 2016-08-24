package com.baseframework.web.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.interceptor.Interceptor;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;

/**
 * @author Jayaraj
 * 
 */
public class CookieInterceptor implements Interceptor {

	private boolean validateCookie() {
		HttpServletRequest request = (HttpServletRequest) ServletActionContext.getRequest();
		Cookie cookie[] = request.getCookies();
		return (cookie == null);
	}

	public String intercept(ActionInvocation invocation) throws Exception {

		if (validateCookie())
			return "cookie_help";

		return invocation.invoke();
	}

	public void destroy() {
		
	}

	public void init() {
		
	}
}
