package spring.jdbc.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import spring.jdbc.dao.ManagementDAO;

public class HelloSpringAOP {

	public static void main(String[] args) throws Exception {
		try {
			global_process();
		} finally {
			Thread.currentThread().sleep(1000 * 5);
		}
	}

	private static void global_process() throws Exception {
		String xml = "resource-examples/spring-jdbc-aop.xml";
		ApplicationContext ctx = new FileSystemXmlApplicationContext(xml);
		ManagementDAO dao = null;
		dao = (ManagementDAO) ctx.getBean("managementDAO");
		dao.selectAllDepartment();
		dao.insertDepartment();
	}
}
