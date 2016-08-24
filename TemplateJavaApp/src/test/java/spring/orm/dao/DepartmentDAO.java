package spring.orm.dao;

import java.util.HashSet;
import java.util.Set;

import hibernate.HelloHibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.SessionFactoryUtils;

import dto.Department;
import dto.Employee;

public class DepartmentDAO {

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

	public void selectAllDepartment() {
		new HelloHibernate().selectAllDepartment(getSession());
	}

	public void insertDepartment() throws Exception {

		Department d = new Department();
		d.setId(33);
		d.setName("free pool");

		Employee e = new Employee();
		e.setId(155447);
		e.setName("Jayaraj's");
		e.setStatus(true);

		Set employees = new HashSet();
		employees.add(e);
		
		e.setDepartment(d);
		d.setEmployee(employees);
		
		Session s = getSession();
		s.save(d);		
		s.save(e);
		s.flush();

		/*
		 * if the below line is commented then data will be inserted else it
		 * will be rollback by spring transaction.
		 */
		throw new RuntimeException("roll back please");
	}

}
