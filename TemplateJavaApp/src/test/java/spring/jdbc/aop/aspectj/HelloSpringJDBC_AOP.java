package spring.jdbc.aop.aspectj;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import spring.jdbc.dao.ManagementDAO;

public class HelloSpringJDBC_AOP {

	public static void main(String[] args) throws Exception {
		try {
			process();
		} finally {
			Thread.currentThread().sleep(1000 * 5);
		}
	}

	private static void process() throws Exception {
		String xml = "spring-jdbc-aop-aspectj.xml";
		ClassPathResource cp = new ClassPathResource(xml);
		XmlBeanFactory bf = new XmlBeanFactory(cp);
		ManagementDAO dao = (ManagementDAO) bf.getBean("managementDAO");
		//dao.insertDepartment();
		dao.selectAllDepartment();
	}
}
