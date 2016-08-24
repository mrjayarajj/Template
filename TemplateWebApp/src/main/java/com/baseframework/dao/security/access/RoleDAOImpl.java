package com.baseframework.dao.security.access;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.orm.hibernate3.SessionFactoryUtils;

import com.baseframework.domain.security.access.Role;

public class RoleDAOImpl implements RoleDAO {

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
	
	public List<Role> selectAllRole() {
		List<Role> list = getSession().createQuery("select r from Role r order by r.roleName asc").list();
		return list;
	}

	public void deleteRole(List<Integer> roleList) {
		Session session = getSession();
		Query query = session.createQuery("delete from Role r where r.roleId in (:ids)");
		query.setParameterList("ids", roleList);
		query.executeUpdate();
	}

	public void insertRole(Role r) {
		Session session = getSession();
		session.save(r);
		session.flush();
	}

	public Role selectRole(int id) {
		Session session = getSession();
		Query query = session.createQuery("select r from Role r where r.roleId = :roleId");
		query.setInteger("roleId", id);
		return (Role) query.list().get(0);
	}

	public void updateRole(Role sourceRole) {
		Session session = getSession();
		Role targetRole = selectRole(sourceRole.getRoleId());
		BeanUtils.copyProperties(sourceRole, targetRole);
		session.saveOrUpdate(targetRole);
		session.flush();
	}

}
