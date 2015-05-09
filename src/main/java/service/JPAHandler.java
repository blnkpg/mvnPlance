package service;

import java.util.HashMap;

public class JPAHandler {

	private HashMap<String, String> registeredDAO = null;

	public JPAHandler() {

	}

	// public void entity() {
	// entityManager.getTransaction();
	// }
	//
	// public void commitEntity() {
	//
	// }
	//
	// public static EntityManager getManager() {
	// return entityManager;
	// }

	// private boolean hasTimeinfo(String classname) {
	// Class[] intefaces;
	// boolean implementsDAOTimestamped = false;
	// try {
	// intefaces = Class.forName(classname).getInterfaces();
	// for (Class clazz : intefaces) {
	// if (clazz.equals(DAOTimestamped.class)) {
	// return true;
	// }
	// }
	// } catch (ClassNotFoundException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	//
	// }
	// return false;
	// }

	/**
	 * Registriert die DAO Klassen und die Klassen die sie repräsentieren mithilfe einer reflections libary https://code.google.com/p/reflections/
	 * 
	 * @param className
	 * @param daoName
	 */
	// private void registerDao(String className , String daoName) {
	// this.registeredDAO = new HashMap<String, String>();
	//
	// Reflections reflections = new Reflections("model.dao.jpa.classes");
	// Set<Class<? extends JPADataAccessObject>> subTypes = reflections.getSubTypesOf(JPADataAccessObject.class);
	// for (Class<? extends JPADataAccessObject> clazz : subTypes) {
	// if (clazz.isAnnotationPresent(DAORegister.class)) {
	// registeredDAO.put(((DAORegister) clazz.getAnnotation(DAORegister.class)).entityName(), clazz.getName());
	// }
	// }
	// }
}
