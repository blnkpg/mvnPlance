package model.dao.jpa;

import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.dao.DataAccessObject;

/**
 * in anlehnung an : <a href="http://java.dzone.com/articles/jpa-implementation-patterns">http://java.dzone.com/articles/jpa-implementation-patterns</a> -
 * Absatz 5 <br/>
 * 
 * @author Christopher Lucas
 * @param <K>
 *            - der Schlüssel der Entität
 * @param <E>
 *            - die eigentliche Entität
 */
public abstract class JPADataAccessObject<K, E> implements DataAccessObject<K, E> {

	protected Class<E> entityClass;

	@PersistenceContext
	protected EntityManager entityManager;

	public JPADataAccessObject() {
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
		this.entityClass = (Class<E>) genericSuperclass.getActualTypeArguments()[1];
	}

	public void persist(E entity) {
		entityManager.persist(entity);
	}

	public void remove(E entity) {
		entityManager.remove(entity);
	}

	public E findById(K id) {
		return entityManager.find(entityClass, id);
	}

	public String getEntityName() {
		return this.entityClass.getName();
	}

}
