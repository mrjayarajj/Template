package com.baseframework.dao.hsqldb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import org.hsqldb.jdbc.JDBCDataSource;

public class Server {

	public static void start(String dbPath) {
		org.hsqldb.Server server = new org.hsqldb.Server();
		server.setPort(9001);
		server.setDatabaseName(0, "BASE");
		server.setDatabasePath(0, dbPath);
		server.setNoSystemExit(true);
		
		server.start();
	}

	public static void stop() throws Exception {
		Class.forName("org.hsqldb.jdbcDriver");
		Connection c = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost:9001/BASE", "SA", "");
		c.createStatement().executeUpdate("SHUTDOWN");
		c.close();
	}

	public static void flush() throws Exception {
		Class.forName("org.hsqldb.jdbcDriver");
		Connection c = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost:9001/BASE", "SA", "");
		c.createStatement().executeUpdate("CHECKPOINT");
		Thread.sleep(1000 * 5);
		c.close();
	}

	public static void main(String[] args) throws Exception {		
		
		JDBCDataSource ds = new JDBCDataSource();
		ds.setUrl("jdbc:hsqldb:hsql://127.0.0.1:9001/BASE");
		ds.setUser("SA");
		ds.setPassword("SA");

		
		Class.forName("org.hsqldb.jdbcDriver");
		//Connection c = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost:9001/BASE", "SA", "SA");
		Connection c = ds.getConnection();
		System.out.println(c);
		select(c);
		flush();
	}

	private static void select(Connection c) throws Exception {
		ResultSet rst = c.createStatement().executeQuery("SELECT * FROM BASE.USER");
		while (rst.next()) {
			System.out.println("WORKING:>" + rst.getString(1));
		}
	}

	
}
