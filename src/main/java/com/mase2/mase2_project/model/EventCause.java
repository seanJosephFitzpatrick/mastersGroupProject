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

	//bi-directional many-to-one association to BaseData
	@OneToMany(mappedBy="eventCause")
	private List<BaseData> baseData;

	public EventCause() {
	}

	public EventCausePK getId() {
		return this.id;
	}

	public void setId(EventCausePK id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<BaseData> getBaseData() {
		return this.baseData;
	}

	public void setBaseData(List<BaseData> baseData) {
		this.baseData = baseData;
	}

	public BaseData addBaseData(BaseData baseData) {
		getBaseData().add(baseData);
		baseData.setEventCause(this);

		return baseData;
	}

	public BaseData removeBaseData(BaseData baseData) {
		getBaseData().remove(baseData);
		baseData.setEventCause(null);

		return baseData;
	}

	public void createRow(ArrayList<String> cells) {
		EventCausePK eventCausePK = new EventCausePK();
		eventCausePK.setEventCode(cells.get(0));
		eventCausePK.setEventId(cells.get(1));
		this.setId(eventCausePK);
		this.setDescription(cells.get(2));
		
	}

}