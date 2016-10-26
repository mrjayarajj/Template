package com.baseframework.web.security.core.userdetails;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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

	public static void main(String args[]) {
		// You can mock concrete classes, not just interfaces
		LinkedList mockedList = mock(LinkedList.class);

		// stubbing
		when(mockedList.get(0)).thenReturn("first");
		when(mockedList.get(1)).thenThrow(new RuntimeException());

		
		System.out.println(mockedList.get(0));
		System.out.println(verify(mockedList).get(0));// Exception in thread
														// "main" Wanted but not
														// invoked:
														// linkedList.get(0);

		
		reset(mockedList);
		
		// stubbing using built-in anyInt() argument matcher
		when(mockedList.get(anyInt())).thenReturn("element");

		for (int i = 0; i < 3; i++) {
			System.out.println(mockedList.get(i));
		}

		// stubbing using custom matcher (let's say isValid() returns your own
		// matcher implementation):
		 //when(mockedList.contains(argThat(isValid()))).thenReturn("element");

	}

	public static Matcher<Boolean> isValid() {
		return null;
	}

	@Test
	public void testOnLoad() {
		UserService us = mock(UserService.class);
		when(us.selectAllRole()).thenReturn(getRoles());
		when(us.selectAllUser()).thenReturn(getUsers());
		ua.setUserService(us);
		String resultName = ua.onLoad();
		Assert.assertEquals(5, ua.getUserForm().getRoles().size());
		Assert.assertEquals(5, ua.getUserForm().getUsers().size());
		Assert.assertEquals("success", resultName);
	}

	@Test
	public void testAddUser() {
		User u = new User(5, "super", "super");
		UserService us = mock(UserService.class);
		doNothing().when(us).insertUser(u);
		UserForm uf = new UserForm();
		uf.setUser(u);
		ua.setUserForm(uf);
		ua.setUserService(us);
		String resultName = ua.addUser();
		Assert.assertEquals("redirect_showUser", resultName);
	}

}
