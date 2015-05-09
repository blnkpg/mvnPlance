package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the payment database table.
 * 
 */
@Entity
@Table(name="payment")
@NamedQuery(name="Payment.findAll", query="SELECT p FROM Payment p")
public class Payment implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PaymentPK id;

	private double amount;

	private byte paid;

	@Column(nullable=false, length=45)
	private String paymentOption;

	//uni-directional many-to-one association to Paidobject
	@ManyToOne
	@JoinColumn(name="PaidObject_poID", nullable=false, insertable=false, updatable=false)
	private Paidobject paidobject;

	public Payment() {
	}

	public PaymentPK getId() {
		return this.id;
	}

	public void setId(PaymentPK id) {
		this.id = id;
	}

	public double getAmount() {
		return this.amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public byte getPaid() {
		return this.paid;
	}

	public void setPaid(byte paid) {
		this.paid = paid;
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

}