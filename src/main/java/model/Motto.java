package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the motto database table.
 * 
 */
@Entity
@Table(name="motto")
@NamedQuery(name="Motto.findAll", query="SELECT m FROM Motto m")
public class Motto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false, length=45)
	private String mottoName;

	//bi-directional many-to-many association to Tag
	@ManyToMany
	@JoinTable(
		name="motto_tag"
		, joinColumns={
			@JoinColumn(name="fk_mt_mottoName", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="tags_tagname", nullable=false)
			}
		)
	private List<Tag> tags;

	public Motto() {
	}

	public String getMottoName() {
		return this.mottoName;
	}

	public void setMottoName(String mottoName) {
		this.mottoName = mottoName;
	}

	public List<Tag> getTags() {
		return this.tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

}