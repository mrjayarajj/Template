package com.baseframework.web.security.core.userdetails;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.baseframework.biz.security.core.userdetails.UserService;
import com.baseframework.domain.security.access.Role;
import com.baseframework.domain.security.core.userdetails.User;

public class UserControllerTest {

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

	}

	// @Test
	public void testCancel() {
		// String resultName = uc.cancel();
		// Assert.assertEquals("redirect_onLoad", resultName);
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
		// when(mockedList.contains(argThat(isValid()))).thenReturn("element");

	}

	public static Matcher<Boolean> isValid() {
		return null;
	}

	@Test
	public void showHomePage() throws Exception {

		UserService us = mock(UserService.class);
		when(us.selectAllUser()).thenReturn(getUsers());

		UserController uc = new UserController();
		uc.setUserService(us);

		MockMvcBuilders.standaloneSetup(uc).build().perform(get("/security/core/userdetails/users"))
				.andExpect(status().isOk()).andExpect(view().name("/jsp/base/security/user.jsp")).andReturn().getResponse().getContentAsString();;
	}

	// @Test
	public void testOnLoad() {
		UserService us = mock(UserService.class);
		when(us.selectAllRole()).thenReturn(getRoles());
		when(us.selectAllUser()).thenReturn(getUsers());
		// uc.setUserService(us);
		// uc.onLoad();
		// Assert.assertEquals(5, uc.getUserForm().getRoles().size());
		// Assert.assertEquals(5, uc.getUserForm().getUsers().size());
	}

	// @Test
	public void testAddUser() {
		User u = new User(5, "super", "super");
		UserService us = mock(UserService.class);
		doNothing().when(us).insertUser(u);
		UserForm uf = new UserForm();
		uf.setUser(u);
		// uc.setUserForm(uf);
		// uc.setUserService(us);
		// uc.addUser(uf);
	}

}
