package com.baseframework.dao.hsqldb;

import java.net.URL;
import java.net.URLClassLoader;

import com.baseframework.web.listeners.HsqldbContextListener;

public class StartServer implements Runnable {
	
	private String dbPath = "";
	
	public StartServer(String dbPath){
		this.dbPath = dbPath;
	}

	/**
	 *
	 * we can use daemon thread when it is invoked from server JVM because
	 * server JVM will be active until it is shutdown.
	 * 
	 *
	 * @param args this is an argument
	 */
	public static void main(String[] args) {
		
		ClassLoader cl = ClassLoader.getSystemClassLoader();

        URL[] urls = ((URLClassLoader)cl).getURLs();

        for(URL url: urls){
        	System.out.println(url.getFile());
        }
        
		if (args != null && args.length > 0 && args[0].equals(HsqldbContextListener.class.getName())) {
			Thread t = new Thread(new StartServer(args[1]));
			t.setDaemon(true);
			t.start();
		} else {
			new StartServer(args[1]).run();
		}
	}

	public void run() {
		Server.start(getDbPath());
	}

	public String getDbPath() {
		return dbPath;
	}
	
}
