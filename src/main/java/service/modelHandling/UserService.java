package service.modelHandling;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import model.Person;
import model.Timeinfo;
import model.User;
import service.PersistenceHandler;

public class UserService extends ModelService<User> {

	public UserService(PersistenceHandler persistence) {
		super(persistence);
		this.managedClass = User.class;
	}

	@Override
	public User getSpecificObject(User crippled) {
		for (User user : cache) {
			if (crippled.getUserID() == user.getUserID() || crippled.getUsername().equals(user.getUsername())) {
				return user;
			} else if (crippled.getPerson().getEmail().equalsIgnoreCase(user.getPerson().getEmail())) {
				return user;
			} else if (crippled.getOauth().equals(user.getOauth()) && crippled.getPerson().getLastname().equals(user.getPerson().getLastname())
					&& crippled.getPerson().getName().equals(user.getPerson().getName())) {
				return user;
			}
		}

		ArrayList<User> results = getCondidtionalObjects(crippled);
		if (results.size() != 1) {
			return null;
		} else {
			return results.get(results.size());
		}
	}

	public User authentize(User crippled) {
		for (User user : cache) {
			if (crippled.getUsername().equals(user.getUsername()) && crippled.getPassword().equals(user.getUsername())) {
				return user;
			}
		}

		HashSet<User> temp = new HashSet<User>();
		fillHashSet(
				temp,
				this.persistence.getPersistenceService().getWhere(managedClass.getName(), new String[] { "username", "password" },
						new String[] { crippled.getUsername(), crippled.getPassword() }));
		if (temp.size() > 1) {
			return null;
		} else {
			return (User) temp.iterator();
		}
	}

	public User signUp(User crippled) {
		Person temp = getPersonFromEmail(crippled.getPerson().getEmail());
		if (temp != null) {
			crippled.setPerson(temp);
		} else {
			this.persistence.getPersistenceService().persist(Person.class, crippled.getPerson());
			crippled.setPerson(getPersonFromEmail(crippled.getPerson().getEmail()));

		}

		if (crippled.getTimeinfo() == null) {
			crippled.setTimeinfo(new Timeinfo());
			crippled.getTimeinfo().setCreateTime(new Date(System.currentTimeMillis()));
		}
		crippled.getTimeinfo().setUpdateTime(new Date(System.currentTimeMillis()));

		this.persistence.getPersistenceService().persist(managedClass, crippled);

		/**
		 * Test
		 */
		User test = new User();
		test.setUsername("codeTest");
		test.setPassword("testpassword");
		Person testPerson = new Person();
		testPerson.setEmail("testimail@brauchs.te");
		test.setPerson(testPerson);
		this.persistence.getPersistenceService().persist(Person.class, test.getPerson());
		test.setPerson(getPersonFromEmail(test.getPerson().getEmail()));

		this.persistence.getPersistenceService().persist(User.class, test);
		/**
		 * test endr
		 */
		User response = getSpecificObject(crippled);
		return response;
	}

	private Person getPersonFromEmail(String email) {
		HashSet<Person> temp = new HashSet<Person>();
		fillPersonHashSet(temp, this.persistence.getPersistenceService().getWhere("Person", new String[] { "email" }, new String[] { email }));
		if (temp.size() != 1) {
			return null;
		} else {
			return temp.iterator().next();
		}
	}

	private void fillPersonHashSet(HashSet<Person> hashset , List results) {
		for (Object object : results) {
			if (managedClass.isInstance(object)) {
				hashset.add((Person) object);
			} else {
				System.err.println("Cannot cast Object");
			}
		}
	}
}
