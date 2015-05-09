package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the celebration database table.
 */
@Entity
@Table(name = "celebration" )
@NamedQuery(name = "Celebration.findAll" , query = "SELECT c FROM Celebration c" )
public class Celebration implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO )
	@Column(unique = true , nullable = false )
	private int c_ID;

	@Column(name = "c_name" , nullable = false , length = 45 )
	private String cName;

	// uni-directional many-to-one association to Category
	@ManyToOne
	@JoinColumn(name = "fk_category_category_id" , nullable = false )
	private Category category;

	// uni-directional many-to-one association to Location
	@ManyToOne
	@JoinColumn(name = "fk_location_location_ID" , nullable = false )
	private Location location;

	// uni-directional many-to-one association to Motto
	@ManyToOne
	@JoinColumn(name = "fk_motto_mottoName" , nullable = false )
	private Motto motto;

	// uni-directional many-to-one association to Timeinfo
	@ManyToOne
	@JoinColumn(name = "fk_timeinfo" , nullable = false )
	private Timeinfo timeinfo;

	// uni-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name = "fk_user_organizer" , nullable = false )
	private User user;

	public Celebration() {
	}

	public int getC_ID() {
		return this.c_ID;
	}

	public void setC_ID(int c_ID) {
		this.c_ID = c_ID;
	}

	public String getCName() {
		return this.cName;
	}

	public void setCName(String cName) {
		this.cName = cName;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Location getLocation() {
		return this.location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Motto getMotto() {
		return this.motto;
	}

	public void setMotto(Motto motto) {
		this.motto = motto;
	}

	public Timeinfo getTimeinfo() {
		return this.timeinfo;
	}

	public void setTimeinfo(Timeinfo timeinfo) {
		this.timeinfo = timeinfo;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}