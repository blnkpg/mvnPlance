package model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 * The primary key class for the payment database table.
 */
@Embeddable
public class PaymentPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	// @Column(insertable=false, updatable=false, unique=true, nullable=false)
	@PrimaryKeyJoinColumn(name = "personID" , referencedColumnName = "person_personID" )
	private int paidObject_poID;

	// @Column(insertable=false, updatable=false, unique=true, nullable=false)
	@PrimaryKeyJoinColumn(name = "personID" , referencedColumnName = "person_personID" )
	private int person_personID;

	public PaymentPK() {
	}

	public int getPaidObject_poID() {
		return this.paidObject_poID;
	}

	public void setPaidObject_poID(int paidObject_poID) {
		this.paidObject_poID = paidObject_poID;
	}

	public int getPerson_personID() {
		return this.person_personID;
	}

	public void setPerson_personID(int person_personID) {
		this.person_personID = person_personID;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PaymentPK)) {
			return false;
		}
		PaymentPK castOther = (PaymentPK) other;
		return (this.paidObject_poID == castOther.paidObject_poID) && (this.person_personID == castOther.person_personID);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.paidObject_poID;
		hash = hash * prime + this.person_personID;

		return hash;
	}
}