package com.baseframework.web.listeners;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestListener implements ServletRequestAttributeListener {
	
	private static final Logger LOG = LoggerFactory.getLogger(RequestListener.class);

	private void show(String msg, ServletRequestAttributeEvent arg0) {
		LOG.debug("REQ:#" + msg + " K->" + arg0.getName() + " V->" + arg0.getValue());
	}

	public void attributeAdded(ServletRequestAttributeEvent arg0) {
		show("ADD", arg0);
	}

	public void attributeRemoved(ServletRequestAttributeEvent arg0) {
		if(LOG.isDebugEnabled()){
			show("REM", arg0);
		}
	}

	public void attributeReplaced(ServletRequestAttributeEvent arg0) {
		if(LOG.isDebugEnabled()){
			show("REP", arg0);
		}
	}
}
