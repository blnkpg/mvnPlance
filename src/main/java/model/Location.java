package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the location database table.
 * 
 */
@Entity
@Table(name="location")
@NamedQuery(name="Location.findAll", query="SELECT l FROM Location l")
public class Location implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int location_ID;

	private int capacity;

	@Column(nullable=false)
	private Object publicity;

	//uni-directional many-to-one association to Address
	@ManyToOne
	@JoinColumn(name="address_address_ID")
	private Address address;

	//bi-directional many-to-many association to Tag
	@ManyToMany
	@JoinTable(
		name="location_tag"
		, joinColumns={
			@JoinColumn(name="locations_location_ID", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="tags_tagname", nullable=false)
			}
		)
	private Set<Tag> tags;

	//uni-directional many-to-one association to Timeinfo
	@ManyToOne
	@JoinColumn(name="fk_timeinfo", nullable=false)
	private Timeinfo timeinfo;

	public Location() {
	}

	public int getLocation_ID() {
		return this.location_ID;
	}

	public void setLocation_ID(int location_ID) {
		this.location_ID = location_ID;
	}

	public int getCapacity() {
		return this.capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public Object getPublicity() {
		return this.publicity;
	}

	public void setPublicity(Object publicity) {
		this.publicity = publicity;
	}

	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Set<Tag> getTags() {
		return this.tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	public Timeinfo getTimeinfo() {
		return this.timeinfo;
	}

	public void setTimeinfo(Timeinfo timeinfo) {
		this.timeinfo = timeinfo;
	}

}