package service.persistence;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NamedQuery;
import javax.persistence.Persistence;

public class JPAService implements PersistenceService {

	private EntityManagerFactory factory;
	private EntityManager entityManager;
	private HashMap<String, Class> daoRegister = new HashMap<String, Class>();

	public JPAService() {
		this.factory = Persistence.createEntityManagerFactory("mvnPlance");// String == Persistence.xml UnitName
		this.entityManager = factory.createEntityManager();
	}

	public List getAllEntityWithNamedQuery(String namedQuery) {
		if (namedQuery.equals("")) {
			return null;
		} else {
			this.entityManager.getTransaction().begin();
			List result = this.entityManager.createNamedQuery(namedQuery).getResultList();
			this.entityManager.getTransaction().commit();
			return result;
		}
	}

	public List getAllEntityWithNativeQuery(String nativeQuery) {
		if (nativeQuery.equals("")) {
			return null;
		} else {
			this.entityManager.getTransaction().begin();
			List result = this.entityManager.createNativeQuery(nativeQuery).getResultList();
			this.entityManager.getTransaction().commit();
			return result;
		}
	}

	@Override
	public List getAllEntity(Class clazz) {
		String query = "";
		for (Annotation annot : clazz.getAnnotations()) {
			if (annot instanceof NamedQuery) {
				query = ((NamedQuery) annot).query();
			}
		}
		return getAllEntityWithNativeQuery(query);
	}

	@Override
	public List getWhere(String tableName , String[] columnNames , String[] columnValues) {
		if (tableName.startsWith("model.")) {
			tableName = tableName.substring(tableName.indexOf('.') + 1);
		}

		String sqlQuery = "SELECT " + listValuesSQLLike(columnNames, "");
		sqlQuery += " FROM " + tableName;
		sqlQuery += " WHERE ";
		sqlQuery += listWhereValues(columnNames, columnValues);
		return getAllEntityWithNativeQuery(sqlQuery);
	}

	@Override
	public List findByPrimaryKey(Class clazz , int primaryKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void persist(Class clazz , Object obj) {
		System.out.println("Klasse persistiert: " + clazz.getName());

		if (!this.entityManager.contains(obj)) {
			this.entityManager.getTransaction().begin();
			this.entityManager.persist(clazz.cast(obj));
			// this.entityManager.flush();
			this.entityManager.getTransaction().commit();
		} else {
			this.entityManager.merge(obj);
		}

	}

	@Override
	public void remove(Class clazz , Object obj) {

	}

	private String listValuesSQLLike(String[] values , String quotation) {
		String result = "";
		for (String value : values) {
			result += quotation + value + quotation + ",";
		}

		result = result.substring(0, result.length() - 1);

		return result;
	}

	private String listWhereValues(String[] columnNames , String[] columnValues) {
		String result = "";
		if (columnNames.length != columnValues.length) {
			return "";
		}

		for (int i = 0; i < columnValues.length; i++) {
			result = columnNames[i] + " = '" + columnValues[i] + "' ";
		}

		return result;
	}
	//
	// private void registerDataAccessObjectClasses() {
	// Reflections reflects = new Reflections("model.ao.jpa");
	//
	// HashSet<Class> classTemp = new HashSet<Class>(reflects.getSubTypesOf(JPADataAccessObject.class));
	// for (Class clazz : classTemp) {
	// String entitiyName = "";
	// for (Annotation annot : clazz.getAnnotations()) {
	// if (annot instanceof DAORegister) {
	// DAORegister temp = (DAORegister) annot;
	// entitiyName = temp.entityName();
	// }
	// }
	// if (!entitiyName.equals("")) {
	// this.daoRegister.put(entitiyName, clazz);
	// }
	//
	// }

	// }
}
