package spring.orm;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import spring.orm.dao.DepartmentDAO;

public class HelloSpringORM {

	public static void main(String[] args) throws Exception {

		try {
			process();
		} finally {
			Thread.currentThread().sleep(1000 * 5);
		}
	}

	private static void process() throws Exception {
		String xml = "spring-hibernate.xml";
		ClassPathResource cp = new ClassPathResource(xml);
		XmlBeanFactory bf = new XmlBeanFactory(cp);
		DepartmentDAO dao = (DepartmentDAO) bf.getBean("departmentDAO");
		dao.insertDepartment();
	}
}
