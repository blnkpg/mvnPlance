package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the timeinfo database table.
 * 
 */
@Entity
@Table(name="timeinfo")
@NamedQuery(name="Timeinfo.findAll", query="SELECT t FROM Timeinfo t")
public class Timeinfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int ts_ID;

	@Temporal(TemporalType.DATE)
	@Column(name="create_time")
	private Date createTime;

	@Temporal(TemporalType.DATE)
	@Column(name="update_time")
	private Date updateTime;

	public Timeinfo() {
	}

	public int getTs_ID() {
		return this.ts_ID;
	}

	public void setTs_ID(int ts_ID) {
		this.ts_ID = ts_ID;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}