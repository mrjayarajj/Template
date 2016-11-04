package com.baseframework.web.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baseframework.dao.hsqldb.Server;

public class HsqldbContextListener implements ServletContextListener {

	private static Logger LOG = LoggerFactory.getLogger(HsqldbContextListener.class);
	
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		
		String osName = System.getProperty("os.name");
		
		/*
		String dbPath = this.getClass().getResource("/").getPath()+"com/baseframework/config/dev/db/hsqldb/base/BASE";
		
		if(osName.contains("Linux")==false){			
			dbPath = dbPath.substring(1);
			dbPath = dbPath.replace("/","\\\\");
			dbPath = dbPath.replace("%20"," ");					
		}	
		*/
		
		String dbPath = "/Users/mrjayarajj/Downloads/hsqldb/BASE";
			
		Server.start(dbPath);
		
		LOG.debug("HsqldbContextListener Started>>>");
	}

	public void contextDestroyed(ServletContextEvent arg0) {
		try {
			Server.stop();
		} catch (Exception e) {
			LOG.error("DB stop called before it is started or not able to stop DB server while stopping the server",e);
		}
		LOG.debug("HsqldbContextListener Stopped>>>");
	}
}
