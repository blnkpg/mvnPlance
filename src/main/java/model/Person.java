package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the person database table.
 * 
 */
@Entity
@Table(name="person")
@NamedQuery(name="Person.findAll", query="SELECT p FROM Person p")
public class Person implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int personID;

	@Column(length=255)
	private String email;

	@Column(length=45)
	private String lastname;

	@Column(length=45)
	private String name;

	@Column(length=45)
	private String pseodo;

	public Person() {
	}

	public int getPersonID() {
		return this.personID;
	}

	public void setPersonID(int personID) {
		this.personID = personID;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPseodo() {
		return this.pseodo;
	}

	public void setPseodo(String pseodo) {
		this.pseodo = pseodo;
	}

}