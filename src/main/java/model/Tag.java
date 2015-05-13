package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the tag database table.
 * 
 */
@Entity
@Table(name="tag")
@NamedQuery(name="Tag.findAll", query="SELECT t FROM Tag t")
public class Tag implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false, length=45)
	private String tagname;

	//bi-directional many-to-many association to Location
	@ManyToMany(mappedBy="tags")
	private Set<Location> locations;

	//bi-directional many-to-many association to Motto
	@ManyToMany(mappedBy="tags")
	private Set<Motto> mottos;

	public Tag() {
	}

	public String getTagname() {
		return this.tagname;
	}

	public void setTagname(String tagname) {
		this.tagname = tagname;
	}

	public Set<Location> getLocations() {
		return this.locations;
	}

	public void setLocations(Set<Location> locations) {
		this.locations = locations;
	}

	public Set<Motto> getMottos() {
		return this.mottos;
	}

	public void setMottos(Set<Motto> mottos) {
		this.mottos = mottos;
	}

}