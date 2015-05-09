package service;

import model.Person;
import model.User;
import service.modelHandling.UserService;

public class MainService {

	private PersistenceHandler persistence = null;
	private AuthService authentification = null;
	private UserService userService = null;

	public MainService() {
		this.persistence = new PersistenceHandler(PersistenceHandler.JPA, PersistenceHandler.MYSQL);
		this.userService = new UserService(persistence);
	}

	public boolean authenticate(String username , String password) {
		User crippled = new User();
		crippled.setUsername(username);
		crippled.setUsername(username);
		User response = this.userService.authentize(crippled);

		return response != null;
	}

	public boolean signUp(String username , String name , String lastname , String email , String password) {
		User crippled = new User();
		crippled.setUsername(username);
		crippled.setPerson(new Person());
		crippled.getPerson().setName(name);
		crippled.getPerson().setLastname(lastname);
		crippled.setPassword(password);
		User response = this.userService.getSpecificObject(crippled);

		if (response == null) {
			crippled.getPerson().setEmail(email);
		}
		crippled = this.userService.signUp(crippled);

		return crippled != null;
	}
}
