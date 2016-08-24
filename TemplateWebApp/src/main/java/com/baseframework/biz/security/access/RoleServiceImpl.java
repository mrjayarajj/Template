package com.baseframework.biz.security.access;

import java.util.List;

import com.baseframework.dao.security.access.RoleDAO;
import com.baseframework.domain.security.access.Role;

public class RoleServiceImpl implements RoleService {
	
	private RoleDAO roleDAO = null;

	public void deleteRole(List<Integer> roleList) {
		getRoleDAO().deleteRole(roleList);
	}

	public void insertRole(Role r) {
		getRoleDAO().insertRole(r);
	}

	public List<Role> selectAllRole() {		
		return getRoleDAO().selectAllRole();
	}

	public Role selectRole(int id) {		
		return getRoleDAO().selectRole(id);
	}

	public void updateRole(Role r) {
		getRoleDAO().updateRole(r);
	}

	public RoleDAO getRoleDAO() {
		return roleDAO;
	}

	public void setRoleDAO(RoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}

}
