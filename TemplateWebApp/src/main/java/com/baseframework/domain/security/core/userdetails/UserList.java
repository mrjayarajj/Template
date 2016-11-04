package com.baseframework.domain.security.core.userdetails;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserList {

	public UserList() {

	}

	public UserList(List<User> users) {
		this.users = users;
	}

	private List<User> users;

	public List<User> getUserList() {
		return users;
	}
	
	public void addUser(User user){
		if(this.users==null){
			this.users = new ArrayList<User>();
		}
		this.users.add(user);
	}

	public void setUserList(List<User> user) {
		this.users = user;
	}

	public String toString() {
		return users.toString();
	}
}
