package service.modelHandling;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import model.dao.jpa.Fetches;
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
						System.err.println("Cannot cast Object");
					}
				}
			}
		}
	}

	public abstract E getSpecificObject(E crippled);

	/**
	 * Erhält ein Angebrochenes objekt der zur verwaltenden Klasse um anhand der gesetzten Attribute innerhalb des Objektes eine Anfrage an die
	 * Persistenzschicht abzugeben.
	 * 
	 * @param - <E> crippled - ein Angebrochenes Objekt der zu verwaltendenklasse, bei dem nicht alle attribute gesetzt sind, so dass man es aus dem
	 *        Benutzungkontext erstellen konnte. (In der Regel sind keine Primärschlüssel zur Identifikation gegeben)
	 * @return - Alle <E> Entitäten aus der Datenbank, deren Attribute mit dem des übergebenen Objektes einstimmen.
	 */
	protected Fetches fetchAttributesAndValues(E crippled) {
		ArrayList<String> columnNames = new ArrayList<String>();
		ArrayList<String> columnValues = new ArrayList<String>();
		Fetches fetches = new Fetches();
		for (Field field : crippled.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			try {
				if (field.get(crippled) != null) {
					if (!field.getName().equals("serialVersionUID")) {
						System.out.println(field.getType().getName() + " " + field.getName());
						switch (field.getType().getName()) {
							case "int":
								columnNames.add(field.getName());
								columnValues.add(String.valueOf(field.getInt(crippled)));

								System.out.println("ColumnName: " + field.getName() + "\tColumnValue " + field.getInt(crippled));
								break;

							case "double":
								columnNames.add(field.getName());
								columnValues.add(String.valueOf(field.getDouble(crippled)));

								System.out.println("ColumnName: " + field.getName() + "\tColumnValue " + field.getDouble(crippled));
								break;

							case "java.lang.String":
								columnNames.add(field.getName());
								columnValues.add((String) field.get(crippled));

								System.out.println("ColumnName: " + field.getName() + "\tColumnValue " + (String) field.get(crippled));
								break;
							default:
								break;
						}
					}

				}

			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for (int i = 0; i < columnNames.size(); i++) {
			fetches.addValue(columnNames.get(i), columnValues.get(i));
		}
		return fetches;
	}

	protected ArrayList<E> getCondidtionalObjects(E crippled) {
		Fetches fetches = fetchAttributesAndValues(crippled);
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
