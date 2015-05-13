package service;

import service.persistence.JDBCService;
import service.persistence.JPAService;
import service.persistence.PersistenceService;

public class PersistenceHandler {

	public static String JPA = "JPA";
	public static String JDBC = "JDBC";
	public static String MYSQL = "MYSQL";
	private PersistenceService pService = null;

	public PersistenceHandler(String persistenMechanism , String dataBaseType) {
		if (persistenMechanism.equals(JPA)) {
			this.pService = new JPAService();
		} else if (persistenMechanism.equals(JDBC)) {
			this.pService = new JDBCService();
		}
	}

	public PersistenceService getPersistenceService() {
		return this.pService;
	}

}
