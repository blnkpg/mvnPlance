package service.modelHandling;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import service.PersistenceHandler;

/**
 * generisches Laden der verwalteten Klasse in anlehnung an : <a
 * href="http://java.dzone.com/articles/jpa-implementation-patterns">http://java.dzone.com/articles/jpa-implementation-patterns</a> - Absatz 5 <br/>
 * 
 * @author Christopher Lucas
 * @param <E>
 *            - die zu verwaltende Klasse
 */
public abstract class ModelService<E> {

	@SuppressWarnings("rawtypes" )
	protected Class managedClass;
	protected PersistenceHandler persistence;
	protected HashSet<E> cache = new HashSet<E>();

	public ModelService(PersistenceHandler persistence) {
		this.persistence = persistence;
	}

	@SuppressWarnings("unchecked" )
	public ModelService() {
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
	}

	public ArrayList<E> getAll_cached() {
		fillHashSet(this.cache, persistence.getPersistenceService().getAllEntity(this.managedClass));
		return new ArrayList<E>(this.cache);
	}

	public ArrayList<E> getAll() {
		HashSet<E> hashset = new HashSet<E>();
		fillHashSet(hashset, persistence.getPersistenceService().getAllEntity(this.managedClass));
		return new ArrayList<E>(hashset);
	}

	@SuppressWarnings("unchecked" )
	protected void fillHashSet(HashSet<E> hashset , List results) {
		if (results != null) {
			if (!results.isEmpty()) {
				for (Object object : results) {
					if (managedClass.isInstance(object)) {
						hashset.add((E) object);
					} else {
						System.err.println("Cannot cast Object to " + managedClass.getName());
					}
				}
			}
		}
	}

	public abstract E getSpecificObject(E crippled);

	protected ArrayList<E> getCondidtionalObjects(E crippled) {
		Fetches fetches = new Fetches(crippled);
		HashSet<E> temp = new HashSet<E>();
		if (this.persistence != null) {
			System.out.println(managedClass.getName());

			if (this.persistence.getPersistenceService() != null) {
				fillHashSet(temp, this.persistence.getPersistenceService().getWhere(managedClass.getName(), fetches.getNames(), fetches.getValues()));
			}
		}

		return new ArrayList<E>(temp);
	}

}
