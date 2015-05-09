package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the address database table.
 * 
 */
@Entity
@Table(name="address")
@NamedQuery(name="Address.findAll", query="SELECT a FROM Address a")
public class Address implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int address_ID;

	@Column(nullable=false, length=3)
	private String country;

	@Column(nullable=false)
	private int number;

	@Column(nullable=false)
	private int postcode;

	@Column(nullable=false, length=45)
	private String street;

	public Address() {
	}

	public int getAddress_ID() {
		return this.address_ID;
	}

	public void setAddress_ID(int address_ID) {
		this.address_ID = address_ID;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getNumber() {
		return this.number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getPostcode() {
		return this.postcode;
	}

	public void setPostcode(int postcode) {
		this.postcode = postcode;
	}

	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

}