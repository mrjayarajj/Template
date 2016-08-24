package hibernate.migrate;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.ReplicationMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.event.PostLoadEvent;
import org.hibernate.event.def.DefaultPostLoadEventListener;

public class MigrateHibernateListener extends DefaultPostLoadEventListener   {
	
	private SessionFactory sessionFactory = null;
	
	public MigrateHibernateListener(){
		System.out.println("Event Object Created..");
		Configuration config = new Configuration();
		config.configure("config\\hibernate\\hsqldb\\hibernate.cfg.xml");
		this.sessionFactory = config.buildSessionFactory();		
	}
	
	public static void main(String[] args) {
		new MigrateHibernateListener();
	}
	
	public void onPostLoad(PostLoadEvent event) {
		Session session = (Session) sessionFactory.openSession();
		Transaction tx = session.getTransaction();

		try {
			tx = session.beginTransaction();
			
			migrate(session,event.getEntity());
			
			session.flush();
			/**
			 * flush happen's automatically, even if u have not written
			 * session.flush()
			 */
			tx.commit();

		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
			//sessionFactory.close();
		}	
	}

	private void migrate(Session session,Object entity) throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
		System.out.println("Migrating....."+entity);
		//entity = BeanUtils.cloneBean(entity);
		session.save(entity);	
	}	

}
