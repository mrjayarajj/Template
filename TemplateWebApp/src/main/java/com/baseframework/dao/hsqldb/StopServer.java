package com.baseframework.dao.hsqldb;

public class StopServer implements Runnable {

	public static void main(String[] args) throws Exception {
		/*no need Daemon thread because of server need to wait unil DB server is shutdown*/
		
		/*
		if (args != null && args.length > 0 && args[0].equals(HsqldbContextListener.class.getName())) {
			Thread t = new Thread(new StopServer());
			t.setDaemon(true);
			t.start();
		} else {
			Server.stop();
		}
		*/
		
		Server.stop();
	}

	public void run() {
		try {
			Server.stop();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
