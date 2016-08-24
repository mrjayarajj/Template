package com.baseframework.dao.security.access;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.baseframework.domain.security.access.Module;

public class ModuleDAOTest {

	
	public void testModuleDAO() {
		ApplicationContext context = new ClassPathXmlApplicationContext("/spring.xml");
		ModuleDAO moduleDAO = context.getBean(ModuleDAO.class, "moduleDAO");
		List<Module> users = moduleDAO.selectAllModule();
		System.out.println(users.get(0).getModuleId());
	}

}
