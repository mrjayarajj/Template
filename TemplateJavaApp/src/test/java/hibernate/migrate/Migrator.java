package hibernate.migrate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.GenericJDBCException;
import org.hibernate.persister.entity.EntityPersister;

public class Migrator {

	private SessionFactory sourceSessionFactory = null;

	private SessionFactory designationSessionFactory = null;

	public Migrator(String sourceConfigurationFilePath) {
		Configuration sourceConfig = new Configuration();
		sourceConfig.configure(sourceConfigurationFilePath);
		sourceSessionFactory = sourceConfig.buildSessionFactory();
	}

	public void migrateTo(String designationConfigurationFilePath) throws Exception {
		Configuration designationConfig = new Configuration();
		designationConfig.configure(designationConfigurationFilePath);
		designationSessionFactory = designationConfig.buildSessionFactory();
		try {
			process();
		} catch (Exception e) {
			throw e;
		} finally {
			designationSessionFactory.close();
		}
	}

	private void process() throws Exception {
		Map metadata = sourceSessionFactory.getAllClassMetadata();
		System.out.println(metadata);

		List<String> laterExecutaleEntity = new ArrayList<String>();

		for (Iterator i = metadata.values().iterator(); i.hasNext();) {

			EntityPersister persister = (EntityPersister) i.next();
			String className = persister.getClassMetadata().getEntityName();

			try {
				copyPaste(className);
			} catch (ConstraintViolationException e) {
				laterExecutaleEntity.add(className);
			}catch (GenericJDBCException e){
				laterExecutaleEntity.add(className);
			}
		}

		for (String className : laterExecutaleEntity) {
			copyPaste(className);
		}
	}

	private void copyPaste(String className) throws Exception {

		Session sourceSession = sourceSessionFactory.openSession();
		List result = sourceSession.createQuery("from " + className + " c").list();
		sourceSession.close();

		for (Object o : result) {
			saveToDesignation(o);
		}

	}

	private void saveToDesignation(Object o) throws Exception {
		Session designationSession = (Session) designationSessionFactory.openSession();
		//Transaction tx = designationSession.getTransaction();

		try {
			//tx = designationSession.beginTransaction();

			designationSession.save(o);

			//tx.commit();

		} catch (Exception e) {
			//tx.rollback();
			throw e;
		} finally {
			designationSession.close();
		}
	}

	public static void main(String[] args) throws Exception {
		Migrator migrator = new Migrator("config\\hibernate\\mysql\\hibernate.cfg.xml");
		migrator.migrateTo("config\\hibernate\\hsqldb\\hibernate.cfg.xml");
	}

}
