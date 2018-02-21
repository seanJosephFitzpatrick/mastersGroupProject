package com.mase2.mase2_project.rest;

import java.util.ArrayList;
import java.util.List;

import com.mase2.mase2_project.model.EventCause;
import com.mase2.mase2_project.model.FailureClass;
import com.mase2.mase2_project.model.MccMnc;
import com.mase2.mase2_project.model.Ue;
import com.mase2.mase2_project.util.FileLogger;
import com.mase2.mase2_project.util.InvalidEntity;

public class Validator {
	
	private List<FailureClass> failureClassData;
	private List<EventCause> eventCauseData;
	private List<MccMnc> mccMncData;
	private List<Ue> ueData;
	private FileLogger fileLogger = new FileLogger();
	private FailureClass failureClassRow;
	private EventCause eventCauseRow;
	private MccMnc mccMncRow;
	private Ue ueRow;
	
	

	public Validator() {
		// TODO Auto-generated constructor stub
	}

	public boolean validateMcc_Mnc(MccMnc mccmnc) {
		boolean validMccMnc = false;
		if((mccmnc.getCountry().matches("^[a-zA-Z -]+$")) && (mccmnc.getOperator().matches("^[a-zA-Z -]+$")) &&
				(mccmnc.getId().getMcc().matches("[0-9]+")) && (mccmnc.getId().getMnc().matches("[0-9]+"))){
			validMccMnc = true;
		}
		return validMccMnc;
		}
	
	public boolean validateEventCause(EventCause eventCause) {
		boolean validEventCause = false;
		if((eventCause.getId().getEventCode().matches("[0-9]+")) && (eventCause.getId().getEventId().matches("[0-9]+")) &&
				(eventCause.getDescription().matches("^[a-zA-Z -]+$"))){
			validEventCause = true;
		}
		
		return validEventCause;
	}
	
	public boolean validateFailureClass(FailureClass failureClass) {
		boolean validFailureClass = false;
		if(failureClass.getFailureClass().matches("[0-4]") && (failureClass.getDescription().matches("^[a-zA-Z -]+$"))){
			validFailureClass = true;
		}
		
		return validFailureClass;
	} 
	
	
	public boolean checkMccMncForeignKeys(ArrayList<String> cells) {
		for (MccMnc mccMnc : mccMncData) {
			if (cells.get(4).equalsIgnoreCase(mccMnc.getId().getMcc())) {
				if (cells.get(5).equalsIgnoreCase(mccMnc.getId().getMnc())) {
					mccMncRow = mccMnc;
					return true;
				}

			}

		}
		return false;
	}

	public boolean checkUeTypeForeignKeys(ArrayList<String> cells) {
		for (Ue ue : ueData) {
			if (cells.get(3).equalsIgnoreCase(ue.getTac())) {
				ueRow = ue;
				return true;
			}
		}
		return false;
	}

	public boolean checkFailureClassForeignKeys(ArrayList<String> cells) {
		if (cells.get(2).equalsIgnoreCase("(null)")) {
			failureClassRow = null;
			return true;
		} else {
			for (FailureClass failureClass : failureClassData) {
				if (cells.get(2).equalsIgnoreCase(failureClass.getFailureClass())) {
					failureClassRow = failureClass;
					return true;
				}
			}
		}
		return false;

	}

	public boolean checkEventCauseForeignKeys(ArrayList<String> cells) {
		if (cells.get(8).equalsIgnoreCase("(null)")) {
			for (EventCause eventCause : eventCauseData) {
				if (cells.get(1).equalsIgnoreCase(eventCause.getId().getEventId())) {
					// placeholder for future datasets
					eventCauseRow = eventCause;
					return true;

				}
			}
		} else {
			for (EventCause eventCause : eventCauseData) {
				if (cells.get(1).equalsIgnoreCase(eventCause.getId().getEventId())) {
					if (cells.get(8).equalsIgnoreCase(eventCause.getId().getEventCode())) {
						eventCauseRow = eventCause;
						return true;
					}
				}
			}
		}
		return false;
	}
	
	
	public FailureClass getFailureClassRow() {
		return failureClassRow;
	}

	public EventCause getEventCauseRow() {
		return eventCauseRow;
	}

	public MccMnc getMccMncRow() {
		return mccMncRow;
	}

	public Ue getUeRow() {
		return ueRow;
	}

	public List<FailureClass> getFailureClassData() {
		return failureClassData;
	}

	public void setFailureClassData(List<FailureClass> failureClassData) {
		this.failureClassData = failureClassData;
	}

	public List<EventCause> getEventCauseData() {
		return eventCauseData;
	}

	public void setEventCauseData(List<EventCause> eventCauseData) {
		this.eventCauseData = eventCauseData;
	}

	public List<MccMnc> getMccMncData() {
		return mccMncData;
	}

	public void setMccMncData(List<MccMnc> mccMncData) {
		this.mccMncData = mccMncData;
	}

	public List<Ue> getUeData() {
		return ueData;
	}

	public void setUeData(List<Ue> ueData) {
		this.ueData = ueData;
	}
	
	

	
	}




