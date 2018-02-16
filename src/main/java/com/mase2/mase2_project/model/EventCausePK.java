package com.mase2.mase2_project.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the event_cause database table.
 * 
 */
@Embeddable
public class EventCausePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="event_id")
	private int eventId;

	@Column(name="event_code")
	private int eventCode;

	public EventCausePK() {
	}
	public int getEventId() {
		return this.eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	public int getEventCode() {
		return this.eventCode;
	}
	public void setEventCode(int eventCode) {
		this.eventCode = eventCode;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof EventCausePK)) {
			return false;
		}
		EventCausePK castOther = (EventCausePK)other;
		return 
			(this.eventId == castOther.eventId)
			&& (this.eventCode == castOther.eventCode);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.eventId;
		hash = hash * prime + this.eventCode;
		
		return hash;
	}
}