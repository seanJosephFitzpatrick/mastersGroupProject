package com.mase2.mase2_project.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the event_cause database table.
 * 
 */
@Entity
@Table(name="event_cause")
@NamedQuery(name="EventCause.findAll", query="SELECT e FROM EventCause e")
public class EventCause implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EventCausePK id;

	private String description;



	public EventCausePK getId() {
		return this.id;
	}

	public void setId(final EventCausePK id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}


	public void createRow(final List<String> cells) {
		final EventCausePK eventCausePK = new EventCausePK();
		eventCausePK.setEventCode(cells.get(0));
		eventCausePK.setEventId(cells.get(1));
		this.setId(eventCausePK);
		this.setDescription(cells.get(2));
		
	}

}