package model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the payment database table.
 */
@Embeddable
@Table(name = "payment" )
@NamedQuery(name = "Payment.findAll" , query = "SELECT p FROM Payment p" )
public class Payment implements Serializable {
	private static final long serialVersionUID = 1L;

	private double amount;

	@Column(nullable = false , length = 45 )
	private String paymentOption;

	// uni-directional many-to-one association to Paidobject
	@ManyToOne
	@JoinColumn(name = "PaidObject_poID" , nullable = false )
	private Paidobject paidobject;

	// uni-directional many-to-one association to Person
	@ManyToOne(cascade = { CascadeType.ALL } )
	@JoinColumn(name = "Person_personID" , nullable = false )
	private Person person;

	public Payment() {
	}

	public double getAmount() {
		return this.amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getPaymentOption() {
		return this.paymentOption;
	}

	public void setPaymentOption(String paymentOption) {
		this.paymentOption = paymentOption;
	}

	public Paidobject getPaidobject() {
		return this.paidobject;
	}

	public void setPaidobject(Paidobject paidobject) {
		this.paidobject = paidobject;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}