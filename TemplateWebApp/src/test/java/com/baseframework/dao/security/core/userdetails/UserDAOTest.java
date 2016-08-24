package com.baseframework.dao.security.core.userdetails;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.baseframework.domain.security.core.userdetails.User;

public class UserDAOTest {

	public static void main(String[] args) {
		new UserDAOTest().testSelectUser();
	}
	
	public void testSelectUser(){
		ApplicationContext context = new ClassPathXmlApplicationContext("/spring.xml");
		UserDAO userDAO = context.getBean(UserDAO.class,"userDAO");
		List<User> users = userDAO.selectAllUser();
		System.out.println(users.get(0).getUsername());
	}
	
}
