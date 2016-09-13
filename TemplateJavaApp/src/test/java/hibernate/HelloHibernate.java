package hibernate;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig.Feature;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.LazyInitializationException;
import org.hibernate.NonUniqueObjectException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.PropertyAccessException;
import org.hibernate.PropertyValueException;
import org.hibernate.Query;
import org.hibernate.QueryException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StaleStateException;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.SQLGrammarException;
import org.hibernate.hql.ast.QuerySyntaxException;
import org.hsqldb.jdbc.JDBCDataSource;

import com.baseframework.domain.security.access.Role;
import com.baseframework.domain.security.core.userdetails.User;
import com.thoughtworks.xstream.XStream;

import dto.Department;
import dto.Employee;

public class HelloHibernate {

	private static final XStream xstream = new XStream();

	static {
		try {
			Context initialContext = new InitialContext(null);
			initialContext.bind("testDS", getDataSource());
		} catch (NamingException e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * static{
	 * 
	 * try { Connection c = getDataSource().getConnection();
	 * c.setAutoCommit(false); Statement s = c.createStatement(); s.
	 * execute("insert into BASE.USER (ROLE_ID, USER_NAME, USER_PASSWORD, GENDER, STATUS, LOCKED, EXPIRE_DATE, USER_ID) values "
	 * + "(1, 'dum', 'pwd', 'M', 'TRUE', 'TRUE', NULL, 4487)"); c.commit();
	 * 
	 * } catch (SQLException e) { e.printStackTrace(); }
	 * 
	 * }
	 */

	public static DataSource getDataSource() {
		JDBCDataSource jdbc = new org.hsqldb.jdbc.JDBCDataSource();
		jdbc.setUrl("jdbc:hsqldb:hsql://127.0.0.1:9001/BASE");
		jdbc.setUser("SA");
		jdbc.setPassword("SA");
		return jdbc;
	}

	public static void main(String[] args) throws Exception {

		try {
			new HelloHibernate().processHibernate();
		} catch (PropertyValueException e) {
			System.err.println(" * set all not-null properties");
			throw e;
		} catch (NonUniqueObjectException e) {
			System.err.println(" * primary key already exist");
			throw e;
		} catch (PropertyAccessException e) {
			System.err.println(" * setted problem with CGLIB");
			throw e;
		} catch (LazyInitializationException e) {
			System.err.println(" * session was closed");
			throw e;
		} catch (QuerySyntaxException e) {
			System.err.println(" * hql query syntax was wrong");
			throw e;
		} catch (QueryException e) {
			System.err.println(" * hql query was wrong");
			throw e;
		} catch (SQLGrammarException e) {
			System.err.println(" * hql query was wrong equalent to sql exception");
			throw e;
		} catch (HibernateException e) {
			System.err.println(" * wrong");
			throw e;
		}
	}

	public void processHibernate() throws Exception {
		Configuration config = new Configuration();
		config.configure(new File(
				"/Users/admin/git/Template/TemplateWebApp/src/main/resources/com/baseframework/config/hibernate/mysql/hibernate.cfg.xml"));
		// config.configure(new
		// File("C:\\git\\mrjayarajj\\Template\\resource-examples\\hibernate.cfg.xml"));
		SessionFactory sessionFactory = config.buildSessionFactory();
		Session session = (Session) sessionFactory.openSession();
		Transaction tx = session.getTransaction();

		try {
			tx = session.beginTransaction();

			learnHibernate(session);
			session.flush();
			/**
			 * flush happen's automatically, even if u have not written
			 * session.flush()
			 */
			tx.commit();

		} catch (Exception e) {
			/*
			 * Exception : org.hibernate.TransactionException: Transaction not
			 * successfully started Reason : java.sql.SQLException: error in
			 * script file line: 2 Unexpected token: ORDER in statement [CREATE
			 * MEMORY TABLE ORDER]
			 * 
			 * Exception : org.hibernate.SessionException: Session was already
			 * closed* Reason : Session already closed
			 */
			if (tx.isActive() && session.isOpen()) {
				tx.rollback();
			}
			throw e;
		} finally {
			/*
			 * Exception : org.hibernate.SessionException: Session was already
			 * closed* Reason : Session already closed
			 */
			if (session.isOpen()) {
				session.close();
			}
			sessionFactory.close();
		}
	}

	public void learnHibernate(Session session) {

		// hql(session);

		// selectAllEmployee(session);

		// selectAllDepartment(session);
		// insertDepartment(session);
		//

		// selectByWhere(session); // Criteria

		// diffGetAndLoad(session);

		insertUser(session);
		// insertEmployee(session);

		// selectAllUpdateAndselectAll(session);
		// insertUpdateDeleteAndSelect(session);
		// insertAndDeleteDepartment(session);
		// selectOneAndUpdate(session); //INFO
		// paging(session);
		// update(session);
	}

	/**
	 * same query dosen't execute two time when u code like this under same
	 * session
	 * 
	 * @param session
	 */
	private void diffGetAndLoad(Session session) {

		// It should be used if you are not sure about the existence of
		// instance.
		// User u = (User) session.get(User.class, 118);//get() method always
		// hit the database.
		// System.out.println(u);//Returns null if object is not found.

		// User jayaraj = (User) session.get(User.class, 226475);//It returns
		// real object not proxy.

		try {
			// It should be used if you are sure that instance exists.
			// User u_ = (User) session.load(User.class, 118);//load() method
			// doesn't hit the database.
			// just returns the reference of an object that might not actually
			// exists, it loads the data from database or cache only when you
			// access other properties of the object.
			// System.out.println(u_);//It returns proxy object.
		} catch (ObjectNotFoundException e) {
			System.err.println("Throws ObjectNotFoundException if object is not found.");
		}
	}

	/**
	 * 
	 * step 1: whole table is divided by fetch size. step 2: retrieve the batch
	 * with the sequence id
	 * 
	 * @param session
	 */
	public void paging(Session session) {

		show(session.createCriteria(Employee.class).list());

		selectEmployee(session, 0, 2); // first two record
		selectEmployee(session, 1, 2); // second two record
		selectEmployee(session, 2, 2); // third two record
	}

	private void selectEmployee(Session session, int batchNo, int fetchSize) {

		Criteria c = session.createCriteria(Employee.class);
		c.setMaxResults(fetchSize);
		c.setFirstResult(batchNo * fetchSize);
		show(c.list());
	}

	private void insertAndDeleteDepartment(Session session) {

		selectAllEmployee(session);
		selectAllDepartment(session);

		Department d = new Department();
		d.setId(33);
		d.setName("BTS PCM");

		Employee jayraj = new Employee();
		jayraj.setId(155447);
		jayraj.setName("Jayaraj Jaganathan");
		jayraj.setDepartment(d);

		Employee tarun = new Employee();
		tarun.setId(155448);
		tarun.setName("tarun");
		tarun.setDepartment(d);

		Set employees = new HashSet();
		employees.add(jayraj);
		employees.add(tarun);

		d.setEmployee(employees);

		session.save(d);

		selectAllEmployee(session);
		selectAllDepartment(session);

		session.delete(d);

		selectAllEmployee(session);
		selectAllDepartment(session);

	}

	/**
	 * update automatically happens before another query execute or when
	 * transaction is commit.
	 * 
	 * @param session
	 */
	private void selectOneAndUpdate(Session session) {
		Employee e = (Employee) session.load(Employee.class, 118);
		e.setName("Himuro 1");
		selectAllEmployee(session);
	}

	/**
	 * org.hibernate.exception.GenericJDBCException: Could not execute JDBC
	 * batch update .
	 * 
	 * @param session
	 */
	private void insertUpdateDeleteAndSelect(Session session) {
		Employee e = insert(session);

		selectAllEmployee(session);
		selectAllDepartment(session);

		update(session, e);

		selectAllEmployee(session);
		selectAllDepartment(session);

		delete(session, e);

		selectAllEmployee(session);
		selectAllDepartment(session);
	}

	/**
	 * Exception in thread "main" org.hibernate.NonUniqueObjectException: a
	 * different object with the same identifier value was already associated
	 * with the session: [dto.Employee#118] <BR>
	 * if you use a select before a update <BR>
	 * you have to use a separate session for the above case or <BR>
	 * use the selected reference object or <BR>
	 * use merge
	 * 
	 * @param session
	 */
	private void selectAllUpdateAndselectAll(Session session) {

		selectAllEmployee(session);
		try {
			update(session);
		} catch (NonUniqueObjectException ex) {
			merge(session);
		}
		selectAllEmployee(session);
	}

	private void merge(Session session) {
		Department d = new Department();
		d.setId(30);
		Employee e = new Employee();
		e.setId(118);
		e.setName("Himuro t:" + System.currentTimeMillis());
		e.setStatus(false);
		e.setDepartment(d);
		session.merge(e);
	}

	private void delete(Session session) {
		try {
			Department d = new Department();
			d.setId(30);
			Employee e = new Employee();
			e.setId(155447);
			e.setName("Jayaraj");
			e.setDepartment(d);
			session.delete(e);
		} catch (StaleStateException e) {
			e.printStackTrace();
		}
	}

	public void selectAllDepartment(Session session) {
		System.err.println(session.getTransaction().isActive());
		Criteria c = session.createCriteria(Department.class);
		c.addOrder(Order.asc("id"));
		List l = c.list();
		show(l);
	}

	private void selectByWhere(Session session) {
		Criteria c = session.createCriteria(Employee.class);
		c.add(Restrictions.eq("id", 204));
		show(c.list());
	}

	private void delete(Session session, Employee e) {
		session.delete(e);
		// session.delete(e.getDepartment());
		// the above line is not needed because we have added(cascade="all")
	}

	private void update(Session session) {
		Department d = new Department();
		d.setId(30);
		Employee e = new Employee();
		e.setId(118);
		e.setName("Himuro t:" + System.currentTimeMillis());
		e.setDepartment(d);
		session.update(e);
		System.out.println("Employee ref become " + e + "because of hibernate");
	}

	private void update(Session session, Employee e) {
		e.setName("Jayaraj Jaganathan");
		e.getDepartment().setName("bench");
		session.saveOrUpdate(e);
	}

	/**
	 * in one department say free pool there can be many employee .
	 * 
	 * @param d
	 *            one object
	 * @param employees
	 *            many employees
	 */
	private void oneToMany(Department d, Set employees) {
		d.setEmployee(employees);
	}

	/**
	 * many employee can be in a department.
	 * 
	 * @param e
	 *            employee
	 * @param d
	 *            department
	 */
	private void manyToOne(Employee e, Department d) {
		e.setDepartment(d);
	}

	public void insertDepartment(Session session) {
		Department d = new Department();
		d.setId(33);
		d.setName("free pool");
		session.save(d);
	}

	public void insertEmployee(Session session) {
		Employee e = new Employee();
		e.setId(155447);
		e.setName("Jayaraj's");
		e.setStatus(true);
		e.setDepartment(new Department());
		session.save(e);
	}
	
	public void updateUser(Session session) {

		User u = new User(new Integer((int) (Math.random() * 1000)).intValue(), "mrj" + Math.random() * 1000,
				"Aug@2016");
		Role r = new Role();
		r.setRoleId(5);
		r.setRoleName("ROLE_ADMIN");
		u.setRole(r);
		session.update(u);

		session.flush();

		new Scanner(System.in).nextLine();

		throw new RuntimeException("validate uncomitted read");

	}


	public void insertUser(Session session) {

		User u = new User(new Integer((int) (Math.random() * 1000)).intValue(), "mrj" + Math.random() * 1000,
				"Aug@2016");
		Role r = new Role();
		r.setRoleId(5);
		r.setRoleName("ROLE_ADMIN");
		u.setRole(r);
		session.save(u);

		session.flush();

		new Scanner(System.in).nextLine();

		throw new RuntimeException("validate uncomitted read");

	}

	public Employee insert(Session session) {

		Department d = new Department();
		d.setId(33);
		d.setName("free pool");

		Employee e = new Employee();
		e.setId(155447);
		e.setName("Jayaraj's");
		e.setStatus(true);

		Set employees = new HashSet();
		employees.add(e);

		/**
		 * it is not necessary to say one to many for an insert but if you use a
		 * select then you will get the relationship without one to many NOTE:
		 * to get the relationship you need to flush (means insert permanently
		 * to database) and refresh your object with refresh
		 */
		// oneToMany(d, employees); //Department 1 --> * Employee
		manyToOne(e, d); // Employee * --> 1 Department

		session.save(e);
		// System.out.println("no sql insert here");

		session.flush();
		// System.out.println("insert execute when u flush");
		// session.refresh(e);

		/**
		 * the below line is not needed because we have added(cascade="all")
		 */
		// session.save(d);
		return e;
	}

	private void hql(Session session) {

		// Query query = session.createQuery("select emp from Employee emp left
		// outer join fetch emp.department ");
		Query query = session.createQuery("select emp from Employee emp");
		// Query query = session.createQuery(" from Employee emp inner join
		// fetch emp.department");
		// Query query = session.createQuery("select dep from Department dep");
		// Query query = session.getNamedQuery("hql_update_department");
		// Query query = session.createQuery("select p from Payment p");
		// Query query = session.createQuery("from Department dept left outer
		// join fetch dept.employee e where 'Whalen' in elements(e.name) order
		// by dept.id ");
		// Query query = session.createQuery("select dept from Department dept
		// ,Employee e where e in elements (dept.employee) and e.name='Whalen'"
		// );
		// Query query = session.createQuery("select u from User u inner join
		// fetch u.role");
		// Query query = session.createQuery("select m from Module m order by
		// m.moduleName asc " );
		/* the count(o.orderId) give a value of 3 but expected in 2 */
		// Query query =
		// session.createQuery("select count(o.orderId) from Order o left outer
		// join o.orderPayments op left outer join o.orderDetails od left outer
		// join od.orderDetailPayments odp ");
		// Query query = session.createQuery("select count(o.orderId) from Order
		// o left outer join o.orderPayments op ");
		// Query query = session.createQuery("select o from Order o left outer
		// join fetch o.orderPayments op left outer join fetch o.orderDetails od
		// left outer join fetch od.orderDetailPayments odp ");
		// Query query = session.createQuery("select
		// od.orderId,count(od.orderDetailId) from OrderDetail od group by
		// od.orderId having count(od.orderDetailId)>1");
		// Collection l = new LinkedHashSet(query.list());
		show(query.list());
		// show(query.list());
		// System.out.println(xstream.toXML(query.list()));

		// duplicateOneToMany(session);
	}

	/**
	 * 
	 * Duplicated entities in a one-to-many select
	 * https://hibernate.onjira.com/browse/HHH-1513
	 * 
	 * @param session
	 */
	private void duplicateOneToMany(Session session) {
		Query query = session.createQuery("from Department dept left outer join fetch dept.employee  "); // WORKING
		show(new HashSet(query.list()));
		show(query.list());
	}

	private void count(Session session) {
		Criteria c = session.createCriteria(Employee.class);
		List l = c.setProjection(Projections.count("id")).list();
		System.out.println(l);
	}

	private void selectAllEmployee(Session session) {
		Criteria c = session.createCriteria(Employee.class);
		c.addOrder(Order.asc("id"));
		List l = c.list();
		show(l);
	}

	private void show(Collection l) {
		System.out.println();
		System.out.println("======================================================");
		for (Object o : l) {
			// System.out.println(xstream.toXML(o));

			ObjectMapper om = new ObjectMapper();
			om.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
			om.setSerializationInclusion(JsonSerialize.Inclusion.NON_EMPTY);
			om.disable(Feature.FAIL_ON_EMPTY_BEANS);
			try {
				String json = om.writerWithDefaultPrettyPrinter().writeValueAsString(o);
				System.out.println(json);
			} catch (JsonGenerationException e) {
				throw new RuntimeException(e);
			} catch (JsonMappingException e) {
				throw new RuntimeException(e);
			} catch (IOException e) {
				throw new RuntimeException(e);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		System.out.println("======================================================");
		System.out.println();
	}
}
