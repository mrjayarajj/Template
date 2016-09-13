package com.baseframework.web.security.core.userdetails;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;

import com.baseframework.biz.security.core.userdetails.UserService;
import com.baseframework.domain.security.access.Role;
import com.baseframework.domain.security.core.userdetails.User;
import com.baseframework.domain.vo.JSONDetails;

public class UserAction implements JSONDetails,SessionAware {

	private UserForm userForm = null;

	private UserService userService = null;
	
	private Map<String,Object> session = null;
	
	private static final Logger LOG = LoggerFactory.getLogger("LC_USER");

	public String cancel() {
		return "redirect_onLoad";
	}

	@Secured({ "BF_VIEW_USER" })
	public String onLoad() {
		List<Role> roleList = getUserService().selectAllRole();
		List<User> userList = getUserService().selectAllUser();
		UserForm f = getUserForm() == null ? new UserForm() : getUserForm();
		f.setUsers(userList);
		f.setRoles(roleList);
		setUserForm(f);
		LOG.debug("Fetched All User information "+f.getUsers());
		return "success";
	}

	@Secured({ "BF_ADD_USER" })
	public String addUser() {
		User u = getUserForm().getUser();
		getUserService().insertUser(u);
		return getUserForm().getRedirectName();
	}

	
	@Secured({ "BF_UPDATE_USER" })
	public String selectUser() {
		User u = getUserForm().getUser();
		User loadedUser = getUserService().selectUserProfile(u.getUserId());
		LOG.debug("selected role for the user:"+loadedUser.getUserId()+" - "+loadedUser.getRole().getRoleId());
		getUserForm().setUser(loadedUser);
		getUserForm().setAction("update");
		return "onLoad";
	}

	@Secured({ "BF_UPDATE_USER" })
	public String updateUser() {
		User u = getUserForm().getUser();
		getUserService().updateUser(u);
		return "redirect_onLoad";
	}

	@Secured({ "BF_DELETE_USER" })
	public String deleteUsers() {
		List<User> userList = getUserForm().getSelectedUserList();
		getUserService().deleteUser(userList);
		return "redirect_onLoad";
	}

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

	/**
	 * session attributes are converted into map and passed
	 */
	public void setSession(Map map) {
		this.session = map;
	}
}
