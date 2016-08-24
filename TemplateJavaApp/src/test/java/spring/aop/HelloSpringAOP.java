package spring.aop;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import spring.jdbc.dao.ManagementDAO;

public class HelloSpringAOP {

	public static void main(String[] args) throws Exception {
		try {
			global_process();
			//process();
		} finally {
			Thread.currentThread().sleep(1000 * 5);
		}
	}

	private static void global_process() throws Exception {
		String xml = "spring-global-aop.xml";
		ApplicationContext ctx = new ClassPathXmlApplicationContext(xml);
		ManagementDAO dao = (ManagementDAO) ctx.getBean("managementDAO");
		dao.selectAllDepartment();
	}

	private static void process() throws Exception {
		String xml = "spring-aop.xml";
		ClassPathResource cp = new ClassPathResource(xml);
		XmlBeanFactory bf = new XmlBeanFactory(cp);
		ManagementDAO dao = (ManagementDAO) bf.getBean("managementDAO");
		dao.selectAllDepartment();
	}
}
