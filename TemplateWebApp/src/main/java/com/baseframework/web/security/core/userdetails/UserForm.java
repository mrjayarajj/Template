package com.baseframework.web.security.core.userdetails;

import java.util.List;

import com.baseframework.domain.security.access.Role;
import com.baseframework.domain.security.core.userdetails.User;
import com.baseframework.error.ErrorInfo;

public class UserForm {

	private ErrorInfo errorInfo = null;

	private String action = "add";

	private String redirectName = "redirect_showUser";

	private User user = null;

	private List<User> users = null;

	private List<Role> roles = null;

	private List<User> selectedUserList = null;

	public ErrorInfo getErrorInfo() {
		return errorInfo;
	}

	public void setErrorInfo(ErrorInfo errorInfo) {
		this.errorInfo = errorInfo;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User User) {
		this.user = User;
	}

	public List<User> getSelectedUserList() {
		return selectedUserList;
	}

	public void setSelectedUserList(List<User> selectedUserList) {
		this.selectedUserList = selectedUserList;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public List<User> getUsers() {
		return users;
	}

	public String getUsersInfoAsString() {
		StringBuffer sb = new StringBuffer();
		for (User u : getUsers()) {
			sb.append(u);
		}
		return sb.toString();
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String getRedirectName() {
		return redirectName;
	}

	public void setRedirectName(String redirectName) {
		this.redirectName = redirectName;
	}
}
