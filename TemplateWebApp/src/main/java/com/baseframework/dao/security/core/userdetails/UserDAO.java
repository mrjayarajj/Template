package com.baseframework.dao.security.core.userdetails;

import java.util.List;

import com.baseframework.domain.security.core.userdetails.User;

public interface UserDAO {

	public void insertUser(User e);
	
	public void insertUser(List<User> userList);

	public List<User> selectAllUser();

	public User selectUserDetailByUserId(int id);

	public User selectUserProfileByUserId(int id);

	public void updateUser(User sourceUser);

	public void deleteUser(List<User> userList);

	public User selectUserByUserName(String userName);

	public void deleteUser(int userID);

}
