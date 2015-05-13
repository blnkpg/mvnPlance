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
			if (crippled.getPerson_personID() == user.getPerson_personID() || crippled.getUsername().equals(user.getUsername())) {
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
			return results.iterator().next();
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
			return temp.iterator().next();
		}
	}

	public User signUp(User crippled) {
		Person temp = getPersonFromEmail(crippled.getPerson().getEmail());
		if (temp != null) {
			crippled.setPerson(temp);
		} else {
			// this.persistence.getPersistenceService().persist(Person.class, crippled.getPerson());
		}

		if (crippled.getTimeinfo() == null) {
			crippled.setTimeinfo(new Timeinfo());
			crippled.getTimeinfo().setCreateTime(new Date(System.currentTimeMillis()));
		}
		crippled.getTimeinfo().setUpdateTime(new Date(System.currentTimeMillis()));
		// this.persistence.getPersistenceService().persist(Timeinfo.class, crippled.getTimeinfo());
		this.persistence.getPersistenceService().persist(User.class, crippled);
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
			if (Person.class.isInstance(object)) {
				// TODO nochmal überprüfen denn eine Person deren Email schon in der db ist wird hier nicht richtig gecastet.... ich glaub das resultset is da
				// nicht richtig
				hashset.add((Person) object);
			} else {
				System.err.println("Cannot cast Object to Class " + Person.class.getName());
			}
		}
	}
}
