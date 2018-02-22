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
	private String eventId="4099";

	@Column(name="event_code")
	private String eventCode;

	public String getEventId() {
		return this.eventId;
	}
	public void setEventId(final String eventId) {
		this.eventId = eventId;
	}
	public String getEventCode() {
		return this.eventCode;
	}
	public void setEventCode(final String eventCode) {
		this.eventCode = eventCode;
	}

	public boolean equals(final Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof EventCausePK)) {
			return false;
		}
		final EventCausePK castOther = (EventCausePK)other;
		return 
			this.eventId.equals(castOther.eventId)
			&& this.eventCode.equals(castOther.eventCode);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.eventId.hashCode();
		hash = hash * prime + this.eventCode.hashCode();
		
		return hash;
	}
}