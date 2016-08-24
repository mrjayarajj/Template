package com.baseframework.biz.security.access;

import java.util.List;

import com.baseframework.domain.security.access.Role;

public interface RoleService {
	public void insertRole(Role r);

	public List<Role> selectAllRole();

	public Role selectRole(int id);

	public void updateRole(Role r);

	public void deleteRole(List<Integer> roleList);
}
