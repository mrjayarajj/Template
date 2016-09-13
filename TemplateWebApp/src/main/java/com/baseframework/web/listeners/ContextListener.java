package com.baseframework.web.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ContextListener implements ServletContextListener {

	private static Logger LOG = LoggerFactory.getLogger(ContextListener.class);

	private HsqldbContextListener hsqldbContextListener = new HsqldbContextListener();

	public void contextInitialized(ServletContextEvent servletContextEvent) {
		LOG.debug("Template Started>>>");
		LOG.debug("");
		LOG.debug("");
		LOG.debug("");
		LOG.debug("");
		LOG.debug("");
		LOG.debug("");
		LOG.debug("");
		LOG.debug("");
		LOG.debug("");
		hsqldbContextListener.contextInitialized(servletContextEvent);
	}

	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		LOG.debug("Template Stopped>>>");
		hsqldbContextListener.contextDestroyed(servletContextEvent);
	}
}
