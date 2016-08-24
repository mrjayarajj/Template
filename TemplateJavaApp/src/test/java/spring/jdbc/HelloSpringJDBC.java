package spring.jdbc;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.IllegalTransactionStateException;

import spring.jdbc.dao.ManagementDAO;

public class HelloSpringJDBC {

	private static ManagementDAO managementDAO = null;

	static Log log = LogFactory.getLog(HelloSpringJDBC.class);

	public static void main(String[] args) throws Exception {

		log.debug("working");

		try {
			String xml = "spring-jdbc.xml";
			ClassPathResource cp = new ClassPathResource(xml);
			XmlBeanFactory bf = new XmlBeanFactory(cp);
			managementDAO = (ManagementDAO) bf.getBean("managementDAO");

			// check_PROPAGATION_REQUIRED_NEW();
			check_PROPAGATION_REQUIRED();
			// check_PROPAGATION_MANDATORY();
			// check_PROPAGATION_NOT_SUPPORTED();
		} finally {
			Thread.currentThread().sleep(1000 * 5);
		}
	}

	/**
	 * it must not need existing tx else throw exception
	 * 
	 * @throws Exception
	 */
	private static void check_PROPAGATION_NOT_SUPPORTED() throws Exception {
		managementDAO.selectAllDepartment();
	}

	/**
	 * it uses exiting tx else create new
	 * 
	 * @throws Exception
	 */
	private static void check_PROPAGATION_REQUIRED() throws Exception {
		managementDAO.insertDepartment();
	}

	/**
	 * it must need existing tx else throw exception
	 * 
	 * @throws Exception
	 */
	private static void check_PROPAGATION_MANDATORY() throws Exception {
		try {
			managementDAO.insertEmpolyee();
		} catch (IllegalTransactionStateException e) {
			System.err.println("employee can be inserted only after inserting the department ");
			e.printStackTrace();
		}
	}

	/**
	 * always use nex tx
	 * 
	 * @throws Exception
	 */
	public static void check_PROPAGATION_REQUIRED_NEW() throws Exception {
		managementDAO.insertDepartmentAndEmployee();
	}
}
