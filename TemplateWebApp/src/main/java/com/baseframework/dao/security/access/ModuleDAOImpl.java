package com.baseframework.dao.security.access;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.stereotype.Repository;

import com.baseframework.domain.security.access.Module;

@Repository("moduleDAO")
public class ModuleDAOImpl implements ModuleDAO {

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

	public void deleteModule(List<Integer> moduleList) {
		Session session = getSession();
		Query query = session.createQuery("delete from Module m where m.moduleId in (:ids)");
		query.setParameterList("ids", moduleList);
		query.executeUpdate();
	}

	public void insertModule(Module m) {
		Session session = getSession();
		session.save(m);
		session.flush();
	}

	public List<Module> selectAllModule() {
		List<Module> list = getSession().createQuery("select m from Module m order by m.moduleName asc").list();
		return list;
	}

	public Module selectModule(int id) {
		Session session = getSession();
		Query query = session.createQuery("select m from Module m where m.moduleId = :moduleId");
		query.setInteger("moduleId", id);
		return (Module) query.list().get(0);
	}

	public void updateModule(Module sourceModule) {
		System.out.println("@@@" + sourceModule.getModuleId());
		Session session = getSession();
		Module targetModule = selectModule(sourceModule.getModuleId());
		BeanUtils.copyProperties(sourceModule, targetModule);
		session.saveOrUpdate(targetModule);
		session.flush();
	}

}
