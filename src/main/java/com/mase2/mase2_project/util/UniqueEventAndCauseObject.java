package com.mase2.mase2_project.util;

import com.mase2.mase2_project.model.EventCause;

public class UniqueEventAndCauseObject {
	private EventCause eventCause;
	private Long count;
	public UniqueEventAndCauseObject(EventCause eventCause, Long count) {
		super();
		this.eventCause = eventCause;
		this.count = count;
	}
	public EventCause getEventCause() {
		return eventCause;
	}
	public void setEventCause(EventCause eventCause) {
		this.eventCause = eventCause;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	
	

}
