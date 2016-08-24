package com.baseframework.web.security.authentication.logout;

import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

public class LogoutAction implements SessionAware {

	private Map session = null;

	public void setSession(Map session) {
		this.session = session;
	}

	public String execute() {
		if (session instanceof SessionMap) {
			((SessionMap) session).invalidate();
		}
		return "success";
	}
}
