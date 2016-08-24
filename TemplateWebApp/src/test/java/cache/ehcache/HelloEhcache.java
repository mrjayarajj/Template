package cache.ehcache;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.baseframework.dao.security.core.userdetails.UserDAO;
import com.baseframework.domain.security.core.userdetails.User;

public class HelloEhcache {

	public static void main(String[] args) throws Exception {

		new HelloEhcache().ehcacheWithSpring();

	}

	private void ehcacheWithSpring() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml", HelloEhcache.class);
		UserDAO userDAO = (UserDAO) applicationContext.getBean("mockUserDAO");

		CacheManager cm = (CacheManager) applicationContext.getBean("cacheManager");
		Cache c = cm.getCache("userCache");

		//userDAO.insertUser(new User(7, "mom", "ex"));
		//userDAO.insertUser(new User(8, "dad", "ex"));
		System.out.println("Select User By User Id :" + userDAO.selectUserDetailByUserId(1));
		System.out.println("Select User By User Id :" + userDAO.selectUserDetailByUserId(1));
		userDAO.updateUser(new User(1, "jayaraj jaganathan", "ex"));
		System.out.println("Select User By User Id :" + userDAO.selectUserDetailByUserId(1));
		
		//List<User> deleteUserList = new ArrayList<User>();
		//deleteUserList.add(new User(1, "jj", "ex"));
		//userDAO.deleteUser(deleteUserList);
		//List<User> insertUserList = new ArrayList<User>();
		//insertUserList.add(new User(7, "momxx", "ex"));
		//insertUserList.add(new User(8, "dadxx", "ex"));
		//userDAO.insertUser(insertUserList);
		//System.out.println("Select All User :" + userDAO.selectAllUser());
		//System.out.println("Select All User :" + userDAO.selectAllUser());
		//System.out.println("Select All User :" + userDAO.selectAllUser());
		
		HelloEhcache.show(c);
		System.out.println(userDAO);

	}

	public static void show(Ehcache e) {
		show((Cache) e);
	}

	public static void show(Cache c) {
		System.out.println();
		System.out.println("-------------------------------------");
		for (Object o : c.getKeys()) {
			if (c.get(o) != null)
				System.out.println(o + "=" + c.get(o).getValue());
			else
				System.out.println(o + "=" + null);
		}
		System.out.println("-------------------------------------");
	}

	public void ehcache() {
		URL url = HelloEhcache.class.getResource("/cache/ehcache/ehcache.xml");
		CacheManager singletonManager = new CacheManager(url);

		// createAnotherCacheManager();
		// addTestCache(singletonManager);

		writeAndRead(singletonManager);
	}

	private void addTestCache(CacheManager cm) {
		int maxElementsInMemory = 50000;
		boolean overflowToDesk = false;
		boolean eternal = false;
		int timeToLiveSecounds = 5;
		int timeIdeal = 2;

		// Note that the eternal attribute, when set to "true", overrides
		// timeToLive and timeToIdle so that no expiration can take place.
		Cache testCache = new Cache("userCache", maxElementsInMemory, overflowToDesk, eternal, timeToLiveSecounds, timeIdeal);

		cm.addCache(testCache);
	}

	private void createAnotherCacheManager() {

		try {
			CacheManager singletonManager2 = new CacheManager();
		} catch (CacheException e) {
			System.err.println("Another unnamed CacheManager already exists");
		}

	}

	private static void writeAndRead(CacheManager singletonManager) {
		Cache testCache = singletonManager.getCache("userCache");
		new CacheWapper(testCache).put("1", "jj");
		new CacheWapper(testCache).put("2", "jp");
		System.out.println(new CacheWapper(testCache).get("2"));
	}
}
