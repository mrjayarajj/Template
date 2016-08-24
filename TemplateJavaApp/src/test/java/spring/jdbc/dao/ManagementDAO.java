package spring.jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;

public class ManagementDAO {

	private DataSource dataSource = null;

	public void selectAllDepartment() throws Exception {
		select(DataSourceUtils.getConnection(getDataSource()));
	}

	public void select(Connection conn) throws Exception {
		System.out.println("AutoCommit Status: " + conn.getAutoCommit());
		Statement smt = conn.createStatement();
		ResultSet rst = smt.executeQuery(SELECT_SQL);
		while (rst.next()) {
			System.out.println(rst.getString(1) + "-" + rst.getString(2));
		}
	}

	public void insertDepartmentAndEmployee() throws Exception {
		insertDepartment();
		insertEmpolyee();
	}

	public void insertEmpolyee() throws Exception {
		insert(DataSourceUtils.getConnection(getDataSource()), INSERT_EMPLOYEE);
		// throw new RuntimeException("roll back please");
	}

	private void usingSpringTemplate() {
		JdbcTemplate t = new JdbcTemplate(getDataSource());
		t.update(INSERT_DEPARTMENT);
	}

	public void insertDepartment() throws Exception {
		/**
		 * this get the connection from spring in which transaction is enabled
		 */
		Connection transactionEnabledConnection = DataSourceUtils.getConnection(getDataSource());
		System.out.println("AutoCommit Status: " + transactionEnabledConnection.getAutoCommit());
		insert(transactionEnabledConnection, INSERT_DEPARTMENT);
		// usingSpringTemplate();
		/**
		 * for unchecked excepting spring transaction do rollback
		 */
		throw new RuntimeException("roll back please");
		/**
		 * for checked excepting spring transaction don't rollback
		 */
		// throw new Exception("Don't roll back please");
	}

	private static final String INSERT_DEPARTMENT = "insert into DEPARTMENT (DEPARTMENT_ID,DEPARTMENT_NAME) values ('101', 'Free')";

	private static final String INSERT_EMPLOYEE = "INSERT INTO EMPLOYEE VALUES(155447,'Jayaraj',50,'Y',NULL,NULL,NULL,NULL)";

	String SELECT_SQL = "SELECT * FROM DEPARTMENT";

	private void insert(Connection conn, String sql) throws Exception {
		System.err.println(conn);
		Statement smt = conn.createStatement();
		System.out.println("Status:>" + smt.executeUpdate(sql));
		smt.close();
	}

	private static Connection getConnection() throws Exception {
		Class.forName("org.hsqldb.jdbcDriver");
		Connection conn = DriverManager.getConnection(
				"jdbc:hsqldb:file:../WelocmeHibernate/db/HIBER", "SA", "");
		return conn;
	}

	public static void main(String[] args) throws Exception {
		Connection conn = getConnection();
		try {
			conn.setAutoCommit(false);
			new ManagementDAO().insert(conn, INSERT_DEPARTMENT);
			throw new RuntimeException("roll back please");
			// new DepartmentDAO().select(conn);
			// conn.commit();
		} catch (RuntimeException e) {
			e.printStackTrace();
			conn.rollback();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Thread.currentThread().sleep(1000 * 5);
		}
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

}
