package service;

import model.User;

public class AuthService {

	private PersistenceHandler persistence = null;

	public AuthService(PersistenceHandler persistence) {
		this.persistence = persistence;
	}

	/**
	 * @param username
	 * @param password
	 * @return - return the <b>User-Object</b> wich matches to these usercredentials. If the username or the password is not right it return <b>null</b>.
	 */
	public User authenticate(String username , String password) {

		// persistence.find(User.class, new String[] { "username", "password" }, new String[] { username, password });
		return null;
	}

	public User signUp(String username , String name , String lastname , String email , String password) {
		return null;
	}
}
