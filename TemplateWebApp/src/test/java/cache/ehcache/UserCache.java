package cache.ehcache;

import java.util.List;

import com.baseframework.dao.security.core.userdetails.UserDAO;
import com.baseframework.domain.security.core.userdetails.User;

public class UserCache implements UserDAO {

	private UserDAO userDAO;

	public UserCache(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public void deleteUser(List<User> userList) {
		userDAO.deleteUser(userList);
	}

	public void insertUser(User e) {

	}

	public List<User> selectAllUser() {
		return null;
	}

	public User selectUserDetailByUserId(int id) {
		return null;
	}

	public User selectUserByUserName(String userName) {
		return null;
	}

	public void updateUser(User sourceUser) {

	}

	public void insertUser(List<User> userList) {
		
	}

	@Override
	public void deleteUser(int userID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User selectUserProfileByUserId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
