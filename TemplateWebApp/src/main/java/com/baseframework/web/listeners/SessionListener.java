package com.baseframework.web.listeners;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SessionListener implements HttpSessionAttributeListener, HttpSessionListener {

	private static final Logger LOG = LoggerFactory.getLogger(SessionListener.class);

	private void show(String msg, HttpSessionBindingEvent arg0) {
		LOG.debug("SES:#" + msg + " K->" + arg0.getName() + " V->" + arg0.getValue());
	}

	public void attributeAdded(HttpSessionBindingEvent arg0) {
		show("ADD", arg0);
	}

	public void attributeRemoved(HttpSessionBindingEvent arg0) {
		show("REM", arg0);
	}

	public void attributeReplaced(HttpSessionBindingEvent arg0) {
		show("REP", arg0);
	}

	public void sessionCreated(HttpSessionEvent se) {
		LOG.debug("Session Created : " + se.getSession().getId());
	}

	public void sessionDestroyed(HttpSessionEvent se) {
		LOG.debug("Session Destroyed : " + se.getSession().getId());
	}

}
