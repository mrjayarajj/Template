package sql.type4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;


public class TestConnection {

	private static void testConnection() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection c = DriverManager.getConnection("jdbc:mysql://mysql-m.sourceforge.net:80/m295036_base",
				"m295036admin", "engineer");
		System.out.println(c);
	}

	private static void testOracleDEVConnection() throws Exception {

		Connection c1 = DriverManager.getConnection("jdbc:oracle:thin:@template.com:1546:templateDb",
				"I_USER", "pwd");

		Properties p = new Properties();
		p.setProperty("user", "I_USER");
		p.setProperty("password", "pwd");

		Connection c = DriverManager.getConnection("jdbc:oracle:thin:@template.com:1546:templateDb", p);
		System.out.println(c);
	}

	private static void testSimpleOracleITConnection() throws Exception {

		Class.forName("oracle.jdbc.driver.OracleDriver");

		Connection c = DriverManager.getConnection("jdbc:oracle:thin:@template.com:1525:templateDb",
				"I_USER", "pwd");

		System.out.println(c);

	}

	private static void testOracleITConnectionUsingJDBC() throws Exception {

		Class.forName("oracle.jdbc.OracleDriver");

		Properties p = new Properties();
		p.setProperty("user", "I_USER");
		p.setProperty("password", "pwd");
		
		//p.setProperty("oracle.net.ssl_version", "1.2");
		p.setProperty("oracle.net.ssl_cipher_suites", "TLS_RSA_WITH_AES_256_CBC_SHA256, TLS_RSA_WITH_AES_128_CBC_SHA256 ");
		p.setProperty("oracle.net.ssl_server_dn_match","true"); 

		sql.type4.tls.SSLProtocolTests.main(null);
		Properties props = System.getProperties();
		props.list(System.out);
		
		Connection c = DriverManager.getConnection("jdbc:oracle:thin:@(DESCRIPTION = (ADDRESS_LIST = (ADDRESS = (PROTOCOL = TCPS) (HOST = template.com) (PORT = 1525))) (CONNECT_DATA = (SID = templateDb)))",p);
		System.out.println(c);

	}

	private static void testOracleITConnectionUsingOracleJar() throws Exception {

		//java.util.Properties p = new java.util.Properties();
		//p.put("oracle.net.ssl_version", "1.2");
		//p.put("oracle.net.ssl_cipher_suites", "TLS_RSA_WITH_AES_256_CBC_SHA256,TLS_RSA_WITH_AES_128_CBC_SHA256");
		

		// specify the datasource object
		//OracleDriver ods = new OracleDriver();
		//ods.setURL("jdbc:oracle:thin:@template.com.com:1525:templateDb");
		String url = "jdbc:oracle:thin:@(DESCRIPTION = (ADDRESS_LIST = (ADDRESS = (PROTOCOL = TCPS) (HOST = template.com ) (PORT = 1525))) (CONNECT_DATA = (SID = templateDb )))";//1546
		//ods.setUser("I_USER");
		//ods.setPassword("pwd");
		//ods.setConnectionProperties(p);
		
		Properties p = new Properties();
		p.setProperty("user", "I_USER");
		p.setProperty("password", "pwd");
		
		
		//SSLProtocolTests.main(null);

		//System.out.println(ods.connect(url, p));
	}

	public static void main(String[] args) throws Exception {
		testOracleITConnectionUsingOracleJar();
		// testOracleDEVConnection();
		// testBaseConnection();
	}

	private static void testBaseConnection() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/base", "root", "root");
		System.out.println(c);
	}
}