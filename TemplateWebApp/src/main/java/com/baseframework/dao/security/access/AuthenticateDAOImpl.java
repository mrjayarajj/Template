package com.baseframework.dao.security.access;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.SessionFactoryUtils;

import com.baseframework.domain.security.access.Authenticate;

public class AuthenticateDAOImpl implements AuthenticateDAO {

	public Session getSession() {
		return SessionFactoryUtils.getNewSession(getSessionFactory());
	}

	private SessionFactory sessionFactory = null;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

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
