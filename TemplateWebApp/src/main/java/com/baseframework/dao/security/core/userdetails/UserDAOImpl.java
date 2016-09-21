package com.baseframework.dao.security.core.userdetails;

import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.stereotype.Repository;

import com.baseframework.domain.security.core.userdetails.User;

/**
 * The @Repository annotation is a specialization of the @Component annotation
 * with similar use and functionality. In addition to importing the DAOs into
 * the DI container, it also makes the unchecked exceptions (thrown from DAO
 * methods) eligible for translation into Spring DataAccessException.
 */
@Repository("userDAO")
public class UserDAOImpl implements UserDAO {

	private static final Logger LOG = LoggerFactory.getLogger("LC_USER");

	public Session getSession() {
		return SessionFactoryUtils.getNewSession(getSessionFactory());
	}

	private SessionFactory sessionFactory = null;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public List<User> selectAllUser() {
		Query query = getSession().createQuery("select u from User u inner join fetch u.role");
		List<User> list = query.list();
		LOG.debug("fetched user info from database");
		return list;
	}

	public void insertUser(User e) {
		Session session = getSession();
		System.out.println(e.getUserId());
		session.save(e);
		session.flush();
	}

	public User selectUserDetailByUserId(int id) {
		Session session = getSession();
		Query query = session.createQuery("select u from User u where u.userId = :userId");
		query.setInteger("userId", id);
		List<User> list = query.list();
		User user = list != null && list.size() > 0 ? list.get(0) : null;

		if (user == null) {
			throw new ObjectNotFoundException(user, User.class.toString());
		}

		return user;
	}

	public User selectUserProfileByUserId(int id) {
		Session session = getSession();
		Query query = session.createQuery(
				"select u from User u inner join fetch u.role r inner join fetch r.functions where u.userId = :userId ");
		query.setInteger("userId", id);
		List<User> list = query.list();
		User user = list != null && list.size() > 0 ? list.get(0) : null;

		if (user == null) {
			throw new ObjectNotFoundException(user, User.class.toString());
		}

		return user;
	}

	public void updateUser(User sourceUser) {
		Session session = getSession();
		User targetUser = selectUserDetailByUserId(sourceUser.getUserId());
		String targetUserPassword = targetUser.getUserPassword();
		if (sourceUser.getUserPassword() == null || sourceUser.getUserPassword().length() == 0) {
			sourceUser.setUserPassword(targetUserPassword);
		}
		session.saveOrUpdate(sourceUser);
		session.flush();

	}

	public void deleteUser(List<User> userList) {
		Session session = getSession();
		Query query = session.createQuery("delete from User u where u.userId in (:ids)");
		query.setParameterList("ids", User.getSelectedUserId(userList));
		query.executeUpdate();
	}

	public User selectUserByUserName(String userName) {
		Session session = getSession();
		Query query = session.createQuery(
				"select u from User u left outer join fetch u.role r left outer join fetch r.functions where u.userName = :userName ");
		query.setString("userName", userName);
		List<User> list = query.list();
		User user = list != null && list.size() > 0 ? list.get(0) : null;

		if (user == null) {
			throw new ObjectNotFoundException(user, User.class.toString());
		}

		return user;
	}

	public void insertUser(List<User> userList) {
	}

	public void deleteUser(int userID) {
		Session session = getSession();
		Query query = session.createQuery("delete from User u where u.userId = :ids");
		query.setInteger("ids", userID);
		query.executeUpdate();
	}
}