package com.baseframework.web.security.core.userdetails;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.baseframework.biz.security.core.userdetails.UserService;
import com.baseframework.domain.security.access.Role;
import com.baseframework.domain.security.core.userdetails.User;
import com.baseframework.domain.vo.JSONDetails;

@Controller
@Scope("prototype")
@RequestMapping(value = "/security/core/userdetails")
public class UserController implements JSONDetails {

	public UserController() {
		LOG.debug("UserController ...");
	}

	@Autowired
	private UserService userService = null;

	private static final Logger LOG = LoggerFactory.getLogger("LC_USER");

	public String cancel() {
		return "redirect_onLoad";
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	@Secured({ "BF_VIEW_USER" })
	public ModelAndView onLoad() {
		List<Role> roleList = getUserService().selectAllRole();
		List<User> userList = getUserService().selectAllUser();
		UserForm f = getUserForm() == null ? new UserForm() : getUserForm();
		f.setUsers(userList);
		f.setRoles(roleList);
		setUserForm(f);
		LOG.debug("Fetched All User information " + f.getUsers());

		ModelAndView model = new ModelAndView("/jsp/base/security/user.jsp");
		model.addObject("userForm", f);
		return model;
	}

	@RequestMapping(value = "/users", method = RequestMethod.POST)
	@Secured({ "BF_ADD_USER" })
	public ModelAndView addUser(@ModelAttribute("userForm") UserForm userform) {
		User u = userform.getUser();
		getUserService().insertUser(u);
		return new ModelAndView("redirect:/mvc/security/core/userdetails/users");
	}

	@RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
	@Secured({ "BF_UPDATE_USER" })
	public ModelAndView selectUser(@PathVariable Integer userId) {
		User loadedUser = getUserService().selectUserProfile(userId);
		LOG.debug("selected role for the user:" + loadedUser.getUserId() + " - " + loadedUser.getRole().getRoleId());
		setUserForm(new UserForm());
		getUserForm().setUser(loadedUser);
		getUserForm().setAction("update");
		return onLoad();
	}

	@RequestMapping(value = "/user/{userId}", method = RequestMethod.POST)
	@Secured({ "BF_UPDATE_USER" })
	public ModelAndView updateUser(@ModelAttribute("userForm") UserForm userform) {
		LOG.debug("updateUser : " + userform.getUser());
		User u = userform.getUser();
		getUserService().updateUser(u);
		return new ModelAndView("redirect:/mvc/security/core/userdetails/users");
	}

	@RequestMapping(value = "/users/delete", method = RequestMethod.POST)
	@Secured({ "BF_DELETE_USER" })
	public ModelAndView deleteUsers(@ModelAttribute("userForm") UserForm userform) {
		List<User> userList = userform.getSelectedUserList();
		getUserService().deleteUser(userList);
		return new ModelAndView("redirect:/mvc/security/core/userdetails/users");
	}

	private UserForm userForm = null;

	public UserForm getUserForm() {
		return userForm;
	}

	public Object getJavaValue() {
		return getUserForm();
	}

	public void setUserForm(UserForm userForm) {
		this.userForm = userForm;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
