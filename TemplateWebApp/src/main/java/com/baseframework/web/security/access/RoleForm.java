package com.baseframework.web.security.access;

import java.util.ArrayList;
import java.util.List;

import com.baseframework.domain.security.access.Role;

/**
 * @author Administrator
 *
 */
public class RoleForm {

	private String action = "add";

	private Role role = null;

	private List<Role> roleList = null;

	private List<Integer> selectedRoleList = null;

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public List<Integer> getSelectedRoleList() {	
		return selectedRoleList;
	}

	public void setSelectedRoleList(List<Integer> selectedRoleList) {
		this.selectedRoleList = selectedRoleList;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

}
