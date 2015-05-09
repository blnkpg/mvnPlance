package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the invite database table.
 * 
 */
@Entity
@Table(name="invite")
@NamedQuery(name="Invite.findAll", query="SELECT i FROM Invite i")
public class Invite implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private InvitePK id;

	private byte participatin;

	//uni-directional many-to-one association to Celebration
	@ManyToOne
	@JoinColumn(name="celebrationID", nullable=false, insertable=false, updatable=false)
	private Celebration celebration;

	public Invite() {
	}

	public InvitePK getId() {
		return this.id;
	}

	public void setId(InvitePK id) {
		this.id = id;
	}

	public byte getParticipatin() {
		return this.participatin;
	}

	public void setParticipatin(byte participatin) {
		this.participatin = participatin;
	}

	public Celebration getCelebration() {
		return this.celebration;
	}

	public void setCelebration(Celebration celebration) {
		this.celebration = celebration;
	}

}