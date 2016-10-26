package com.baseframework.rws.security.core.userdetails;

import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.baseframework.biz.security.core.userdetails.UserService;
import com.baseframework.domain.security.access.Role;
import com.baseframework.domain.security.core.userdetails.User;


public class UserRestControllerImpl implements UserRestController {

	public static final String DUMMY_USERS = "/dummy";
	public static final String USERS_BY_ID = "/{id}";
	public static final String USERS_BY_USER_NAME = "/name";
	public static final String USERS = "/users";

	private static final Logger LOG = LoggerFactory.getLogger(UserRestControllerImpl.class);

	@Autowired
	private UserService userService;

	@Autowired
	@Qualifier("userService")
	private UserDetailsService userDetailsService;

	/**
	 * if only need response as JSON produces = MediaType.APPLICATION_JSON_VALUE
	 * 
	 * @return
	 */
	@RequestMapping(value = DUMMY_USERS, method = RequestMethod.GET)
	public @ResponseBody User getDummyUser() {
		LOG.info("Start getDummyUser");
		User user = new User();
		user.setUserName("mrjayarajj");
		user.setUserPassword("engineer");
		return user;
	}

	@RequestMapping(value = USERS_BY_ID, method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<User> getUser(@PathVariable("id") int userID) {
		LOG.info("Start getUser. ID=" + userID);
		User detachedObj = null;
		try {
			detachedObj = userService.selectUserProfile(userID);
			detachedObj.setRole(new Role(detachedObj.getRole().getRoleId()));
			LOG.debug("User:" + detachedObj.getUserId() + " Role:" + detachedObj.getRole().getRoleName());
		} catch (ObjectNotFoundException e) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(detachedObj, HttpStatus.OK);
	}

	@RequestMapping(value = USERS_BY_USER_NAME, method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<User> getUserWithRole(@RequestParam("userName") String userName) {
		LOG.info("Start getUser. userName=" + userName);
		UserDetails detachedObj = null;
		try {
			detachedObj = userDetailsService.loadUserByUsername(userName);
		} catch (ObjectNotFoundException e) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>((User) detachedObj, HttpStatus.OK);
	}

	@RequestMapping(value = USERS, method = RequestMethod.GET)
	public @ResponseBody List<User> getAllUsers() {
		LOG.info("Start getAllUsers.");
		List<User> users = userService.selectAllUser();

		return users;
	}

	@RequestMapping(value = USERS, method = RequestMethod.POST)
	public @ResponseBody User createUser(@RequestBody User user) {
		LOG.info("Start createUser.");
		userService.insertUser(user);
		return user;
	}

	@RequestMapping(value = USERS, method = RequestMethod.DELETE)
	public @ResponseBody User deleteUser(@PathVariable("id") int userID) {
		LOG.info("Start deleteUser.");
		User user = userService.selectUserDetail(userID);
		userService.deleteUser(userID);
		return user;
	}

}