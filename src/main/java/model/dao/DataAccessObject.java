package model.dao;

public interface DataAccessObject<K, E> {
	void persist(E entity);

	void remove(E entity);

	E findById(K id);

	final String SELECTALL = "SELECT * FROM e";
}
