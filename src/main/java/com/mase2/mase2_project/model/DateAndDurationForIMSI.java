package com.mase2.mase2_project.model;

import java.util.Date;

public class DateAndDurationForIMSI {
	private int duration;
	private Date dateTime;

	public DateAndDurationForIMSI(int duration, Date dateTime) {
		super();
		this.duration = duration;
		this.dateTime = dateTime;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

}
