package com.baseframework.web.listeners;

import java.sql.SQLException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.hsqldb.jdbc.JDBCDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baseframework.dao.hsqldb.StartServer;

public class HsqldbContextListener implements ServletContextListener {

	private static Logger LOG = LoggerFactory.getLogger(HsqldbContextListener.class);

	public static void main(String[] args) throws SQLException {
		JDBCDataSource ds = new JDBCDataSource();
		ds.setUrl("jdbc:hsqldb:hsql://127.0.0.1:9001/BASE");
		ds.setUser("SA");
		ds.setPassword("");
		
		System.out.println(ds.getConnection());
	}
	
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
		
		String dbPath = "C:/Users/jjayaraj/Google Drive/Code/db/BASE";
		
		StartServer.main(new String[] { HsqldbContextListener.class.getName() , dbPath });
	
		LOG.debug("HsqldbContextListener Started>>>");
	}

	public void contextDestroyed(ServletContextEvent arg0) {
		try {
			//StopServer.main(new String[] { HsqldbContextListener.class.getName() });
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		LOG.debug("HsqldbContextListener Stopped>>>");
	}
}
