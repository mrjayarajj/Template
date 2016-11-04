package com.baseframework.rws.security.core.userdetails;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.web.client.RestTemplate;

import com.baseframework.biz.security.core.userdetails.UserService;
import com.baseframework.domain.security.access.Role;
import com.baseframework.domain.security.core.userdetails.User;
import com.baseframework.domain.security.core.userdetails.UserList;

public class UserRestControllerTest {

	public List<User> getDummyUsers() {
		List<User> users = new ArrayList<User>();
		users.add(new User(1, "jack", "jackpwd", new Role(1)));
		users.add(new User(2, "ram", "rampwd", new Role(2)));
		users.add(new User(3, "peter", "peterpwd", new Role(3)));
		return users;
	}

	@Test
	public void testUsers() throws Exception {
		UserRestController uc = new UserRestController();
		UserService us = mock(UserService.class);
		when(us.selectUserProfile(1)).thenReturn(getDummyUsers().get(0));
		uc.setUserService(us);
		MockMvc springMockMvc = standaloneSetup(uc).build();

		ResultActions resultActions = springMockMvc.perform(get("/v1.1/security/core/userdetails/users.json?userId=1"))

				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))

				.andExpect(jsonPath("$.userList[0].userId", is(getDummyUsers().get(0).getUserId().intValue())))
				.andExpect(jsonPath("$.userList[0].userName", is(getDummyUsers().get(0).getUserName())))
				.andExpect(jsonPath("$.userList[0].role.roleId", is(getDummyUsers().get(0).getRole().getRoleId())));

		resultActions.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void testUsersJSON() throws Exception {

		UserRestController uc = new UserRestController();
		UserService us = mock(UserService.class);
		when(us.selectAllUser()).thenReturn(getDummyUsers());
		uc.setUserService(us);
		MockMvc springMockMvc = standaloneSetup(uc).build();

		ResultActions resultActions = springMockMvc
				.perform(get("/v1.1/security/core/userdetails/users").header("Accept", "application/json"))

				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.userList", hasSize(3)))

				.andExpect(jsonPath("$.userList[0].userId", is(getDummyUsers().get(0).getUserId().intValue())))
				.andExpect(jsonPath("$.userList[0].userName", is(getDummyUsers().get(0).getUserName())))
				.andExpect(jsonPath("$.userList[0].role.roleId", is(getDummyUsers().get(0).getRole().getRoleId())))

				.andExpect(jsonPath("$.userList[1].userId", is(getDummyUsers().get(1).getUserId().intValue())))
				.andExpect(jsonPath("$.userList[1].userName", is(getDummyUsers().get(1).getUserName())))
				.andExpect(jsonPath("$.userList[1].role.roleId", is(getDummyUsers().get(1).getRole().getRoleId())))

				.andExpect(jsonPath("$.userList[2].userId", is(getDummyUsers().get(2).getUserId().intValue())))
				.andExpect(jsonPath("$.userList[2].userName", is(getDummyUsers().get(2).getUserName())))
				.andExpect(jsonPath("$.userList[2].role.roleId", is(getDummyUsers().get(2).getRole().getRoleId())));

		resultActions.andDo(MockMvcResultHandlers.print());

	}

	@Test
	public void testUsersXML() throws Exception {

		UserRestController uc = new UserRestController();
		UserService us = mock(UserService.class);
		when(us.selectAllUser()).thenReturn(getDummyUsers());
		uc.setUserService(us);
		MockMvc springMockMvc = standaloneSetup(uc).build();

		ResultActions resultActions = springMockMvc
				.perform(get("/v1.1/security/core/userdetails/users").header("Accept", "application/xml"))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_XML));

		resultActions.andDo(MockMvcResultHandlers.print());

	}

	public static void main(String[] args) {

		final String USERS = "http://localhost:8080/rws/v1.1/security/core/userdetails/users";

		final String CREATE_USER = "http://localhost:8080/rws/v1.1/security/core/userdetails/users";

		RestTemplate restTemplate = new RestTemplate();

		SimpleClientHttpRequestFactory rf = (SimpleClientHttpRequestFactory) restTemplate.getRequestFactory();
		rf.setReadTimeout(1 * 400);// 400 milli sec
		rf.setConnectTimeout(1 * 200);// 200 milli sec

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

		ResponseEntity<UserList> result = restTemplate.exchange(USERS, HttpMethod.GET, entity, UserList.class);
		System.out.println(result.getBody());

		ResponseEntity<User> userResult = null;

		Map<String, String> userNameParams = new HashMap<String, String>();
		userNameParams.put("userName", "mrjayarajj");
		userResult = restTemplate.exchange(USERS + "?userName={userName}", HttpMethod.GET, entity, User.class,
				userNameParams);
		System.out.println(userResult.getBody());

		Map<String, Integer> userIdParams = new HashMap<String, Integer>();
		userIdParams.put("userId", 226475);
		userResult = restTemplate.exchange(USERS + "?userId={userId}", HttpMethod.GET, entity, User.class,
				userIdParams);
		System.out.println(userResult.getBody());

		User resultIn = restTemplate.postForObject(CREATE_USER, userResult.getBody(), User.class);
		System.out.println(resultIn);

		// User user = restTemplate.getForObject(GET_USER, User.class);
		// System.out.println(user);
	}

}
