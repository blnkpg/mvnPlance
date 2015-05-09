package service;

import service.jpa.JPAService;
import service.jpa.PersistenceService;

public class PersistenceHandler {

	public static String JPA = "JPA";
	public static String MYSQL = "MYSQL";
	private PersistenceService pService = null;

	public PersistenceHandler(String persistenMechanism , String dataBaseType) {
		if (persistenMechanism.equals("JPA")) {
			this.pService = new JPAService();
		}
	}

	public PersistenceService getPersistenceService() {
		return this.pService;
	}

}
