package model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * The persistent class for the user database table.
 */
@Entity
@Table(name = "user" )
@NamedQuery(name = "User.findAll" , query = "SELECT u FROM User u" )
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO )
	@Column(unique = true , nullable = false )
	private int userID;

	@Lob
	private byte[] oauth;

	@Column(nullable = false , length = 32 )
	private String password;

	@Column(nullable = false , length = 16 )
	private String username;

	// uni-directional one-to-one association to Person
	@OneToOne(cascade = CascadeType.PERSIST )
	@JoinColumn(name = "person_personID" )
	private Person person;

	// uni-directional many-to-one association to Timeinfo
	@OneToOne(cascade = CascadeType.PERSIST )
	@JoinColumn(name = "fk_timeinfo" , nullable = false )
	private Timeinfo timeinfo;

	public User() {
	}

	public int getUserID() {
		return this.userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public byte[] getOauth() {
		return this.oauth;
	}

	public void setOauth(byte[] oauth) {
		this.oauth = oauth;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Timeinfo getTimeinfo() {
		return this.timeinfo;
	}

	public void setTimeinfo(Timeinfo timeinfo) {
		this.timeinfo = timeinfo;
	}

}