package com.baseframework.dao.security.access;

import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.stereotype.Repository;

import com.baseframework.domain.security.access.Function;
import com.baseframework.domain.security.access.Module;

@Repository("functionDAO")
public class FunctionDAOImpl implements FunctionDAO {

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

	public List<Function> selectFunctions(Module module) {
		Session session = getSession();
		Query query = session
				.createQuery("select f from Function f where module.moduleId = :moduleId order by f.functionName asc");
		query.setInteger("moduleId", module.getModuleId());
		return (List<Function>) query.list();
	}

	public void deleteFunction(List<Integer> functionList) {
		Session session = getSession();
		Query query = session.createQuery("delete from Function f where f.functionId in (:ids)");
		query.setParameterList("ids", functionList);
		query.executeUpdate();
	}

	public void insertFunction(Function f) {
		Session session = getSession();
		session.save(f);
		session.flush();
	}

	public List<Function> selectAllFunctionsWithModule() {
		List<Function> list = getSession().createQuery("select f from Function f inner join fetch f.module").list();
		return list;
	}

	public Function selectFunction(int id) {
		Session session = getSession();
		Query query = session.createQuery("select f from Function f where f.functionId = :functionId");
		query.setInteger("functionId", id);
		return (Function) query.list().get(0);
	}

	public void updateFunction(Function sourceFunction) {
		Session session = getSession();
		Function targetFunction = selectFunction(sourceFunction.getFunctionId());
		BeanUtils.copyProperties(sourceFunction, targetFunction);
		session.saveOrUpdate(targetFunction);
		session.flush();
	}

	public List<Function> selectFunctionWithRoles(Module module) {
		Query query = getSession().createQuery(
				"select distinct f from Function f join fetch f.roles where f.module.moduleId = :moduleId ");
		query.setInteger("moduleId", module.getModuleId());
		return query.list();
	}
}
