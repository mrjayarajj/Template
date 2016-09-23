package com.baseframework.dao.security.access;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.stereotype.Repository;

import com.baseframework.domain.security.access.Authenticate;

@Repository("authenticateDAO")
public class AuthenticateDAOImpl implements AuthenticateDAO {

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

	public List<Authenticate> selectAuthenticatedList() {
		List<Authenticate> list = getSession().createQuery("select a from Authenticate a").list();
		return list;
	}

	public void insertAuthenticate(List<Authenticate> grantList) {
		Session session = getSession();
		for (Authenticate a : grantList) {
			if (a.isGrant()) {
				session.saveOrUpdate(a);
			} else {
				session.delete(a);
			}
		}
		session.flush();
	}
}
