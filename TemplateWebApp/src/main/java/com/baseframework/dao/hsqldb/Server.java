package com.baseframework.dao.hsqldb;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import org.hsqldb.jdbc.JDBCDataSource;
import org.hsqldb.persist.HsqlProperties;
import org.hsqldb.server.ServerAcl.AclFormatException;

public class Server {

	public static void start(String dbPath) {
		org.hsqldb.Server server = new org.hsqldb.Server();
		server.setPort(9001);
		server.setDatabaseName(0, "BASE");
		server.setDatabasePath(0, dbPath);
		server.setNoSystemExit(true);
		server.setDaemon(true);
		// server.setLogWriter(IoBuilder.forLogger().setLevel(Level.DEBUG).buildPrintWriter());
		HsqlProperties h = new HsqlProperties();
		h.setProperty("hsqldb.applog", 3);
		try {
			server.setProperties(h);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (AclFormatException e) {
			throw new RuntimeException(e);
		}
		server.start();
	}

	public static void stop() throws Exception {
		Class.forName("org.hsqldb.jdbcDriver");
		Connection c = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost:9001/BASE", "SA", "SA");
		c.createStatement().executeUpdate("SHUTDOWN");
		c.close();
	}

	public static void flush() throws Exception {
		Class.forName("org.hsqldb.jdbcDriver");
		Connection c = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost:9001/BASE", "SA", "SA");
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
		// Connection c =
		// DriverManager.getConnection("jdbc:hsqldb:hsql://localhost:9001/BASE",
		// "SA", "SA");
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
