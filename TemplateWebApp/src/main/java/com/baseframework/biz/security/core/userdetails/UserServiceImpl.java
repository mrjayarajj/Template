package com.baseframework.biz.security.core.userdetails;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baseframework.dao.security.access.RoleDAO;
import com.baseframework.dao.security.core.userdetails.UserDAO;
import com.baseframework.domain.security.access.Role;
import com.baseframework.domain.security.core.userdetails.User;

@Service("userService")
public class UserServiceImpl implements UserService, UserDetailsService {

	@Autowired
	private Md5PasswordEncoder md5PasswordEncoder;

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private RoleDAO roleDAO;

	private static final Logger LOG = LoggerFactory.getLogger("LC_USER");

	public UserServiceImpl() {
		LOG.info("UserServiceImpl constructor is called");
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void insertUser(User u) {
		u.setUserPassword(md5PasswordEncoder.encodePassword(u.getUserPassword(), u.getUserId()));
		getUserDAO().insertUser(u);
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = true)
	public List<User> selectAllUser() {
		LOG.debug("going to select all users from database");
		return getUserDAO().selectAllUser();
	}

	public List<Role> selectAllRole() {
		return getRoleDAO().selectAllRole();
	}

	public User selectUserDetail(int id) {
		User user = getUserDAO().selectUserDetailByUserId(id);
		user.setUserPassword(null);
		return user;
	}

	public User selectUserProfile(int id) {
		User user = getUserDAO().selectUserProfileByUserId(id);
		user.setUserPassword(null);
		return user;
	}

	public void updateUser(User u) {
		if (u.getUserPassword() != null && u.getUserPassword().length() > 0) {
			u.setUserPassword(md5PasswordEncoder.encodePassword(u.getUserPassword(), u.getUserId()));
		}
		getUserDAO().updateUser(u);
	}

	public void deleteUser(List<User> UserList) {
		getUserDAO().deleteUser(UserList);
	}

	public RoleDAO getRoleDAO() {
		return roleDAO;
	}

	public void setRoleDAO(RoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}

	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException, DataAccessException {
		User user = userDAO.selectUserByUserName(userName);
		return user;
	}

	public Md5PasswordEncoder getMd5PasswordEncoder() {
		return md5PasswordEncoder;
	}

	public void setMd5PasswordEncoder(Md5PasswordEncoder md5PasswordEncoder) {
		this.md5PasswordEncoder = md5PasswordEncoder;
	}

	public void deleteUser(int userID) {
		userDAO.deleteUser(userID);
	}
}
