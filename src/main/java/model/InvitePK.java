package model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 * The primary key class for the invite database table.
 */
@Embeddable
public class InvitePK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@PrimaryKeyJoinColumn(name = "celebrationID" , referencedColumnName = "c_ID" )
	// @Column(insertable = false , updatable = false , unique = true , nullable = false )
	private int celebrationID;

	// @Column(insertable=false, updatable=false, unique=true, nullable=false)
	@PrimaryKeyJoinColumn(name = "personID" , referencedColumnName = "person_personID" )
	private int personID;

	public InvitePK() {
	}

	public int getCelebrationID() {
		return this.celebrationID;
	}

	public void setCelebrationID(int celebrationID) {
		this.celebrationID = celebrationID;
	}

	public int getPersonID() {
		return this.personID;
	}

	public void setPersonID(int personID) {
		this.personID = personID;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof InvitePK)) {
			return false;
		}
		InvitePK castOther = (InvitePK) other;
		return (this.celebrationID == castOther.celebrationID) && (this.personID == castOther.personID);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.celebrationID;
		hash = hash * prime + this.personID;

		return hash;
	}
}