package service.modelHandling;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Fetches {

	private Map<String, String> values = new<String, String> HashMap();
	private ArrayList<String> names = new ArrayList<String>();
	private String[] nameArray = null;
	private String[] valueArray = null;

	/**
	 * Erhält ein Angebrochenes objekt der zur verwaltenden Klasse um anhand der gesetzten Attribute innerhalb des Objektes eine Anfrage an die
	 * Persistenzschicht abzugeben.
	 * 
	 * @param - <E> crippled - ein Angebrochenes Objekt der zu verwaltendenklasse, bei dem nicht alle attribute gesetzt sind, so dass man es aus dem
	 *        Benutzungkontext erstellen konnte. (In der Regel sind keine Primärschlüssel zur Identifikation gegeben)
	 * @return - Alle <E> Entitäten aus der Datenbank, deren Attribute mit dem des übergebenen Objektes einstimmen.
	 */
	public Fetches(Object crippled) {
		ArrayList<String> columnNames = new ArrayList<String>();
		ArrayList<String> columnValues = new ArrayList<String>();
		for (Field field : crippled.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			try {
				if (field.get(crippled) != null) {
					if (!field.getName().equals("serialVersionUID")) {
						// System.out.println(field.getType().getName() + " " + field.getName());
						switch (field.getType().getName()) {
							case "int":
								columnNames.add(field.getName());
								columnValues.add(String.valueOf(field.getInt(crippled)));

								// System.out.println("ColumnName: " + field.getName() + "\tColumnValue " + field.getInt(crippled));
								break;

							case "double":
								columnNames.add(field.getName());
								columnValues.add(String.valueOf(field.getDouble(crippled)));

								// System.out.println("ColumnName: " + field.getName() + "\tColumnValue " + field.getDouble(crippled));
								break;

							case "java.lang.String":
								columnNames.add(field.getName());
								columnValues.add((String) field.get(crippled));

								// System.out.println("ColumnName: " + field.getName() + "\tColumnValue " + (String) field.get(crippled));
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
			addValue(columnNames.get(i), columnValues.get(i));
		}

	}

	public void addValue(String name , String value) {
		this.names.add(name);
		this.values.put(name, value);
	}

	public String[] getNames() {
		if (this.nameArray == null) {
			this.toArray();
		}

		return this.nameArray;
	}

	public String[] getValues() {
		if (this.valueArray == null) {
			this.toArray();
		}

		return this.valueArray;
	}

	private void toArray() {
		this.nameArray = new String[names.size()];
		this.valueArray = new String[values.size()];

		for (int i = 0; i < this.nameArray.length; i++) {
			this.nameArray[i] = this.names.get(i);
			this.valueArray[i] = this.values.get(nameArray[i]);
		}
	}
}
