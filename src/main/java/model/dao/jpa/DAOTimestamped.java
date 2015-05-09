package model.dao.jpa;

import java.sql.Date;
import java.util.List;

public abstract class DAOTimestamped<K, E> /* extends JPADataAccessObject<K, E> */{

	List<E> lastEditedSince(Date date) {
		// Query q = entityManager.createQuery(DataAccessObject.SELECTALL + entityClass.getName() + " e WHERE" + UPDATED + ">= :date_since");
		// q.setParameter("date_since", date);
		// return (List<E>) q.getResultList();
		return null;
	}

	List<E> createdSince(Date date) {
		// Query q = entityManager.createQuery(DataAccessObject.SELECTALL + entityClass.getName() + " e WHERE" + CREATED + ">= :date_since");
		// q.setParameter("date_since", date);
		// return (List<E>) q.getResultList();
		//
		return null;
	}

	final String UPDATED = "update_time";
	final String CREATED = "create_time";
}
