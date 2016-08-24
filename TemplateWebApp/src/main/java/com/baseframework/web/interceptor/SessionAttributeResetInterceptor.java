package com.baseframework.web.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * @author Jayaraj
 * 
 */
public class SessionAttributeResetInterceptor implements Interceptor {

	public String intercept(ActionInvocation invocation) throws Exception {

		if (invocation.getAction() instanceof SessionResetAware) {
			SessionResetAware action = ((SessionResetAware) invocation.getAction());
			SessionResetForm form = action.getSessionResetForm();
			if (form != null) {
				form.reset();
			}
		}

		return invocation.invoke();
	}

	public void destroy() {

	}

	public void init() {

	}
}
