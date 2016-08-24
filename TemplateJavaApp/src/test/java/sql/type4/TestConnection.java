package sql.type4;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestConnection {

	private static void testConnection() throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		Connection c = DriverManager.getConnection("jdbc:mysql://mysql-m.sourceforge.net:80/m295036_base","m295036admin","engineer");
		System.out.println(c);
	}
	
	public static void main(String[] args) throws Exception {
		//testConnection();
		testBaseConnection();
	}

	private static void testBaseConnection()throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/base","root","root");
		System.out.println(c);
	}
}