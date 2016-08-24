package cache.ehcache;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.baseframework.dao.security.core.userdetails.UserDAO;
import com.baseframework.domain.security.core.userdetails.User;
import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.KeyGenerator;
import com.googlecode.ehcache.annotations.PartialCacheKey;
import com.googlecode.ehcache.annotations.Property;
import com.googlecode.ehcache.annotations.TriggersRemove;
import com.googlecode.ehcache.annotations.When;

public class MockUserDAOImpl implements UserDAO {

	public MockUserDAOImpl() {
		userList.add(new User(1, "jay", "!"));
		userList.add(new User(2, "grs", "!"));
		userList.add(new User(3, "ram", "!"));
		userList.add(new User(4, "sam", "!"));
		userList.add(new User(5, "nik", "!"));
		userList.add(new User(6, "rsk", "!"));

		System.out.println(userList);
	}

	public List<User> userList = new ArrayList<User>();

	@TriggersRemove(cacheName = "userCache", triggersRemoveInteceptorName = "argumentTriggersRemoveInteceptor", when = When.AFTER_METHOD_INVOCATION, removeAll = true)
	public void deleteUser(List<User> userList) {
		delay("deleteUser");
		for (User srcUser : userList) {
			this.userList.remove(srcUser);
		}
	}

	@Cacheable(cacheName = "userCache", cacheableInteceptorName = "argumentCacheableInterceptor")
	public void insertUser(User e) {
		delay("insertUser");
		for (User u : userList) {
			if (u.hasUserProperty("userId", new Integer(e.getUserId()).toString())) {
				throw new RuntimeException("User Id already exist " + u.getUserId());
			}
		}
		userList.add(e);
	}

	@Cacheable(cacheName = "userCache", cacheableInteceptorName = "argumentCacheableInterceptor")
	public void insertUser(List<User> userList) {
		delay("insertUser as list");
		for (User u : userList) {
			for (User dbU : this.userList) {
				if (dbU.hasUserProperty("userId", new Integer(u.getUserId()).toString())) {
					throw new RuntimeException("User Id already exist " + u.getUserId());
				}
			}
			this.userList.add(u);
		}
	}

	public void delay(String jobName) {
		try {
			System.out.print(jobName + " ");
			for (int i = 0; i < 5; i++) {
				Thread.currentThread().sleep(1000);
				System.out.print(".");
			}
			System.out.println();
		} catch (InterruptedException e) {
			new RuntimeException(e);
		}
	}

	@Cacheable(cacheName = "userCache", cacheableInteceptorName = "returnAllCacheableInterceptor")
	public List<User> selectAllUser() {
		delay("selectAllUser");
		userList.add(new User(9, "sar", "!"));
		userList.add(new User(10, "pbu", "!"));

		List<User> allUserList = new ArrayList<User>();
		for (User u : userList) {
			allUserList.add(u.clone());
		}
		return allUserList;
	}

	@Cacheable(cacheName = "userCache", cacheableInteceptorName = "returnAllCacheableInterceptor")
	public User selectUserDetailByUserId(int id) {
		delay("selectUserByUserId");
		for (User u : userList) {
			if (u.hasUserProperty("userId", new Integer(id).toString())) {
				return u.clone();
			}
		}
		return null;
	}

	@Cacheable(cacheName = "userCache", keyGenerator = @KeyGenerator(name = "ListCacheKeyGenerator", properties = @Property(name = "includeMethod", value = "false")))
	public User selectUserByUserName(@PartialCacheKey String userName) {
		delay("selectUserByUserName");
		for (User u : userList) {
			if (u.hasUserProperty("userName", userName)) {
				return u.clone();
			}
		}
		return null;
	}

	@Cacheable(cacheName = "userCache", cacheableInteceptorName = "argumentCacheableInterceptor")
	public void updateUser(User sourceUser) {
		delay("updateUser");
		for (User u : userList) {
			if (u.hasUserProperty("userId", new Integer(sourceUser.getUserId()).toString())) {
				try {
					BeanUtils.copyProperties(u, sourceUser);
				} catch (Exception e) {
					new RuntimeException(e);
				}
			}
		}
	}

	public String toString() {
		return userList.toString();
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
