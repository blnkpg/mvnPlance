package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the paidobject database table.
 * 
 */
@Entity
@Table(name="paidobject")
@NamedQuery(name="Paidobject.findAll", query="SELECT p FROM Paidobject p")
public class Paidobject implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int poID;

	private int amount;

	@Column(length=45)
	private String name;

	private double price;

	@Column(length=255)
	private String reflink;

	//uni-directional many-to-one association to Celebration
	@ManyToOne
	@JoinColumn(name="fk_c_ID", nullable=false)
	private Celebration celebration;

	public Paidobject() {
	}

	public int getPoID() {
		return this.poID;
	}

	public void setPoID(int poID) {
		this.poID = poID;
	}

	public int getAmount() {
		return this.amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getReflink() {
		return this.reflink;
	}

	public void setReflink(String reflink) {
		this.reflink = reflink;
	}

	public Celebration getCelebration() {
		return this.celebration;
	}

	public void setCelebration(Celebration celebration) {
		this.celebration = celebration;
	}

}