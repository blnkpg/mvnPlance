package service.persistence;

import java.util.List;

public interface PersistenceService {

	public List getAllEntity(Class clazz);

	public List getWhere(String tableName , String[] columnNames , String[] columnValues);

	public List findByPrimaryKey(Class clazz , int primaryKey);

	public void persist(Class clazz , Object obj);

	public void remove(Class clazz , Object obj);

}
