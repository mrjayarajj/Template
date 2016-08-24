package com.baseframework.config.spring;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpringSecurityConfig {

	public void testSpringSecurityConfig() {
		try {
			ApplicationContext context = new ClassPathXmlApplicationContext(
					"com//baseframework//config//spring//spring.xml");
			Assert.assertNotNull(context);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
