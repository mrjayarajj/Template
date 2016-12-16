package com.baseframework.rws.security.core.userdetails;

import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.baseframework.biz.security.core.userdetails.UserService;
import com.baseframework.domain.security.access.Function;
import com.baseframework.domain.security.access.Role;
import com.baseframework.domain.security.core.userdetails.User;
import com.baseframework.domain.security.core.userdetails.UserList;
import com.baseframework.error.ErrorInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.JsonSchemaGenerator;

@RestController
@RequestMapping(value = "/v1.1/security/core/userdetails")
public class UserRestController {

	public static final String DUMMY_USER = "/user/dummy";
	
	public static final String USER = "/user";
	
	public static final String USER_ID = "/user/{userId}";
	public static final String USER_ID_XML = "/user/{userId}.xml";
	public static final String USER_ID_JSON = "/user/{userId}.json";
	
	public static final String USERS = "/users";
	public static final String USERS_JSON = "/users.json";
	public static final String USERS_XML = "/users.xml";
	
	public static final String USERS_SCHEMA = "/users.jsd";

	private static final Logger LOG = LoggerFactory.getLogger(UserRestController.class);

	@ExceptionHandler({ ObjectNotFoundException.class })
	protected ResponseEntity<ErrorInfo> handle(ObjectNotFoundException e) {
		ErrorInfo error = new ErrorInfo("NoUserDataFoundRequest", e.getMessage());
		return new ResponseEntity<ErrorInfo>(error, HttpStatus.NOT_FOUND);
	}

	@Autowired
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Autowired
	@Qualifier("userService")
	private UserDetailsService userDetailsService;

	/**
	 * if only need response as JSON produces = MediaType.APPLICATION_JSON_VALUE
	 * 
	 * @return
	 */
	@RequestMapping(value = DUMMY_USER, method = RequestMethod.GET)
	public @ResponseBody User getDummyUser() {
		LOG.info("Start getDummyUser");
		User user = new User();
		user.setUserName("mrjayarajj");
		user.setUserPassword("engineer");
		return user;
	}

	@RequestMapping(value = USERS_SCHEMA, method = RequestMethod.GET)
	public @ResponseBody JsonSchema getUserByIdSchema() throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		// configure mapper, if necessary, then create schema generator
		JsonSchemaGenerator schemaGen = new JsonSchemaGenerator(mapper);
		JsonSchema schema = schemaGen.generateSchema(User.class);
		return schema;
	}

	public static void main(String[] args) throws JsonProcessingException {
		new UserRestController().getUserByIdSchema();
	}

	@RequestMapping(value = USERS_JSON, params = { "userId" }, method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<UserList> getUserByUserIdAsJSON(@RequestParam("userId") int userId) {
		return getUserByUserIdAsUserList(userId);
	}

	@RequestMapping(value = USERS_XML, params = { "userId" }, method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<UserList> getUserByUserIdAsXML(@RequestParam("userId") int userId) {
		return getUserByUserIdAsUserList(userId);
	}
	
	@RequestMapping(value = USER_ID, method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<User> getUserByUserIdAsUserInResponseEntity(
			@PathVariable("userId") int userId) {
		User detachedObj = getUserByUserIdAsUser(userId);
		return new ResponseEntity<User>(detachedObj, HttpStatus.OK);
	}
	
	@RequestMapping(value = USER_ID_XML, method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<User> getUserByUserIdAsUserAsXML(
			@PathVariable("userId") int userId) {
		User detachedObj = getUserByUserIdAsUser(userId);
		return new ResponseEntity<User>(detachedObj, HttpStatus.OK);
	}

	@RequestMapping(value = USER_ID_JSON, method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<User> getUserByUserIdAsUserAsJSON(
			@PathVariable("userId") int userId) {
		User detachedObj = getUserByUserIdAsUser(userId);
		return new ResponseEntity<User>(detachedObj, HttpStatus.OK);
	}

	private User getUserByUserIdAsUser(int userId) {
		User detachedObj = userService.selectUserProfile(userId);
		detachedObj.setRole(new Role(detachedObj.getRole().getRoleId()));
		LOG.debug("User:" + detachedObj.getUserId() + " Role:" + detachedObj.getRole().getRoleName());
		return detachedObj;
	}

	@RequestMapping(value = USERS, params = { "userId" }, method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<UserList> getUserByUserIdAsUserList(@RequestParam("userId") int userId) {
		LOG.info("Start getUser. ID=" + userId);
		UserList userList = new UserList();
		try {
			User detachedObj = getUserByUserIdAsUser(userId);
			userList.addUser(detachedObj);

		} catch (ObjectNotFoundException e) {
			return new ResponseEntity<UserList>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<UserList>(userList, HttpStatus.OK);
	}

	@RequestMapping(value = USERS_JSON, params = { "userName" }, method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<UserList> getUserByUserNameAsJSON(@RequestParam("userName") String userName) {
		return getUserByUserName(userName);
	}

	@RequestMapping(value = USERS_XML, params = { "userName" }, method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<UserList> getUserByUserNameAsXML(@RequestParam("userName") String userName) {
		return getUserByUserName(userName);
	}

	@RequestMapping(value = USERS, params = { "userName" }, method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<UserList> getUserByUserName(@RequestParam("userName") String userName) {
		LOG.info("Start getUser. userName=" + userName);
		UserList userList = new UserList();
		try {
			User detachedObj = (User) userDetailsService.loadUserByUsername(userName);
			for (Function f : detachedObj.getRole().getFunctions()) {
				f.setModule(null);
			}
			userList.addUser(detachedObj);

		} catch (ObjectNotFoundException e) {
			return new ResponseEntity<UserList>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<UserList>(userList, HttpStatus.OK);
	}

	@RequestMapping(value = USERS_JSON, method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<UserList> getAllUsersAsJSON() {
		return getAllUsers();
	}

	@RequestMapping(value = USERS_XML, method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<UserList> getAllUsersAsXML() {
		return getAllUsers();
	}

	@RequestMapping(value = USERS, method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<UserList> getAllUsers() {
		LOG.info("Start getAllUsers.");
		List<User> users = userService.selectAllUser();

		for (User u : users) {
			u.getRole().setFunctions(null);
		}

		if (users.size() == 0) {
			return new ResponseEntity<UserList>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<UserList>(new UserList(users), HttpStatus.OK);
	}

	@RequestMapping(value = USER, method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<User> createUser(@RequestBody User user) {
		LOG.info("Start createUser.");
		try {
			userService.insertUser(user);
		} catch (ConstraintViolationException e) {
			return new ResponseEntity<User>(HttpStatus.ALREADY_REPORTED);
		}
		return new ResponseEntity<User>(HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = USER_ID_XML, method = RequestMethod.DELETE)
	public @ResponseBody ResponseEntity<User> deleteUser(@PathVariable("id") int userID) {
		LOG.info("Start deleteUser.");
		try {
			User user = userService.selectUserDetail(userID);
			userService.deleteUser(userID);
		} catch (Exception e) {
			return new ResponseEntity<User>(HttpStatus.EXPECTATION_FAILED);
		}

		return new ResponseEntity<User>(HttpStatus.ACCEPTED);
	}

}