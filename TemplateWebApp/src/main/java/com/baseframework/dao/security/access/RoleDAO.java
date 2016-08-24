package com.baseframework.dao.security.access;

import java.util.List;

import com.baseframework.domain.security.access.Role;

public interface RoleDAO {
	public void insertRole(Role r);

	public List<Role> selectAllRole();

	public Role selectRole(int id);

	public void updateRole(Role sourceRole);

	public void deleteRole(List<Integer> roleList);
}
