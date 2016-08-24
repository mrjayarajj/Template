package com.baseframework.web.security.core.userdetails;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.baseframework.biz.security.core.userdetails.UserService;
import com.baseframework.domain.security.access.Role;
import com.baseframework.domain.security.core.userdetails.User;

public class UserActionTest {

	private UserAction ua = null;

	private List<Role> getRoles() {
		List<Role> roles = new ArrayList<Role>();
		roles.add(new Role("ROLE_ADMIN"));
		roles.add(new Role("ROLE_USER"));
		roles.add(new Role("ROLE_GUEST"));
		roles.add(new Role("ROLE_MANAGER"));
		roles.add(new Role("ROLE_ANONYMOUS"));
		return roles;
	}

	private List<User> getUsers() {
		List<User> users = new ArrayList<User>();
		users.add(new User(0, "admin", "admin"));
		users.add(new User(1, "user", "user"));
		users.add(new User(2, "guest", "guest"));
		users.add(new User(3, "manager", "manager"));
		users.add(new User(4, "anon", "anon"));
		return users;
	}

	@Before
	public void setup() {
		UserAction ua = new UserAction();
		this.ua = ua;
	}

	@Test
	public void testCancel() {
		String resultName = ua.cancel();
		Assert.assertEquals("redirect_onLoad", resultName);
	}

	@Test
	public void testOnLoad() {
		UserService us = Mockito.mock(UserService.class);
		Mockito.when(us.selectAllRole()).thenReturn(getRoles());
		Mockito.when(us.selectAllUser()).thenReturn(getUsers());
		ua.setUserService(us);
		String resultName = ua.onLoad();
		Assert.assertEquals(5, ua.getUserForm().getRoles().size());
		Assert.assertEquals(5, ua.getUserForm().getUsers().size());
		Assert.assertEquals("success", resultName);
	}

	@Test
	public void testAddUser() {
		User u = new User(5, "super", "super");
		UserService us = Mockito.mock(UserService.class);
		Mockito.doNothing().when(us).insertUser(u);
		UserForm uf = new UserForm();
		uf.setUser(u);
		ua.setUserForm(uf);
		ua.setUserService(us);
		String resultName = ua.addUser();
		Assert.assertEquals("redirect_showUser", resultName);
	}

}
