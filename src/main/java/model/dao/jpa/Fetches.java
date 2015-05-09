package model.dao.jpa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Fetches {

	private Map<String, String> values = new<String, String> HashMap();
	private ArrayList<String> names = new ArrayList<String>();
	private String[] nameArray = null;
	private String[] valueArray = null;

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
