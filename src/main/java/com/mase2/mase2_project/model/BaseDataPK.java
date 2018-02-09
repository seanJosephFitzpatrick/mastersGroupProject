package com.mase2.mase2_project.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the base_data database table.
 * 
 */
@Embeddable
public class BaseDataPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="event_id", insertable=false, updatable=false, unique=true, nullable=false)
	private int eventId;

	@Column(name="cause_code", insertable=false, updatable=false, unique=true, nullable=false)
	private int causeCode;

	public BaseDataPK() {
	}
	public int getEventId() {
		return this.eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	public int getCauseCode() {
		return this.causeCode;
	}
	public void setCauseCode(int causeCode) {
		this.causeCode = causeCode;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof BaseDataPK)) {
			return false;
		}
		BaseDataPK castOther = (BaseDataPK)other;
		return 
			(this.eventId == castOther.eventId)
			&& (this.causeCode == castOther.causeCode);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.eventId;
		hash = hash * prime + this.causeCode;
		
		return hash;
	}
}