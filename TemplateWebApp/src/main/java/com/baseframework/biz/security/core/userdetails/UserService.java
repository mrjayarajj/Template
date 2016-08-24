package com.baseframework.biz.security.core.userdetails;

import java.util.List;

import com.baseframework.domain.security.access.Role;
import com.baseframework.domain.security.core.userdetails.User;

public interface UserService {

	public void insertUser(User u);

	public List<User> selectAllUser();
	
	public List<Role> selectAllRole();
	
	public User selectUserDetail(int userID);
	
	public User selectUserProfile(int userID);

	public void updateUser(User u);

	public void deleteUser(List<User> usereList);
	
	public void deleteUser(int userID);

}
