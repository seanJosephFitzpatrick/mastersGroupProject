package com.mase2.mase2_project.rest;


import java.util.List;

import com.mase2.mase2_project.model.BaseData;
import com.mase2.mase2_project.model.EventCause;
import com.mase2.mase2_project.model.FailureClass;
import com.mase2.mase2_project.model.MccMnc;
import com.mase2.mase2_project.model.Ue;

public class Validator {

	private List<FailureClass> failureClassData;
	private List<EventCause> eventCauseData;
	private List<MccMnc> mccMncData;
	private List<Ue> ueData;
	private FailureClass failureClassRow;
	private EventCause eventCauseRow;
	private MccMnc mccMncRow;
	private Ue ueRow;



	public boolean validateMcc_Mnc(final MccMnc mccMnc) {
		boolean validMccMnc = false;
		if((mccMnc.getCountry().matches("^[a-zA-Z -]+$")) && (mccMnc.getOperator().matches("^[a-zA-Z 0-9/&. -]+$")) &&
				(mccMnc.getId().getMcc().matches("[0-9]+")) && (mccMnc.getId().getMnc().matches("[0-9]+"))){
			validMccMnc = true;
		}
		return validMccMnc;
	}

	public boolean validateBase_data(final BaseData baseData) {
		boolean validMccMnc = false;
		if((baseData.getCellId().matches("[0-9]+")) && (Integer.toString(baseData.getDuration()).matches("[0-9]+")) &&
				(baseData.getNeVersion().matches("[0-9 a-zA-Z]+")) && (baseData.getImsi().matches("[0-9]+"))
				&& (baseData.getHier321Id().matches("[0-9]+"))&& (baseData.getHier32Id().matches("[0-9]+"))&& (baseData.getHier3Id().matches("[0-9]+"))){
			validMccMnc = true;
		}
		return validMccMnc;
	}

	public boolean validateEventCause(final EventCause eventCause) {
		boolean validEventCause = false;
		if((eventCause.getId().getEventCode().matches("[0-9]+")) && (eventCause.getId().getEventId().matches("[0-9]+")) &&
				(eventCause.getDescription().matches("^[a-zA-Z 0-9 -]+$"))){
			validEventCause = true;
		}

		return validEventCause;
	}

	public boolean validateFailureClass(final FailureClass failureClass) {
		boolean validFailureClass = false;
		if(failureClass.getFailureClass().matches("[0-4]") && (failureClass.getDescription().matches("^[a-zA-Z -]+$"))){
			validFailureClass = true;
		}

		return validFailureClass;
	} 

	public boolean validateUe(final Ue ue) {
		boolean validUe = false;
		if((ue.getTac().matches("[0-9]+")) && (ue.getMarketingName().matches("[a-zA-Z-() 0-9/.]+")) &&
				(ue.getManufacturer().matches("[a-zA-Z-(),& 0-9/.]+")) && (ue.getAccessCapability().matches("(([a-zA-Z-() 0-9/.])+(,)?)+")) &&
				(ue.getModel().matches("[a-zA-Z-() 0-9/.]+")) && (ue.getVendorName().matches("[a-zA-Z-(), 0-9/.&]+")) &&
				(ue.getUeType().matches("[a-zA-Z 0-9-()]+") || ue.getUeType() == null) && (ue.getOs().matches("[a-zA-Z-()]+") || ue.getOs() == null) && 
				(ue.getInputType().matches("[a-zA-Z-()_]+") || ue.getInputType() == null)){
			validUe = true;
		}
		return validUe;
	}

	public boolean checkMccMncForeignKeys(final List<String> cells) {
		for (final MccMnc mccMnc : mccMncData) {
			if (cells.get(4).equalsIgnoreCase(mccMnc.getId().getMcc())) {
				if (cells.get(5).equalsIgnoreCase(mccMnc.getId().getMnc())) {
					mccMncRow = mccMnc;
					return true;
				}

			}

		}
		return false;
	}

	public boolean checkUeTypeForeignKeys(final List<String> cells) {
		for (final Ue ue : ueData) {
			if (cells.get(3).equalsIgnoreCase(ue.getTac())) {
				ueRow = ue;
				return true;
			}
		}
		return false;
	}

	public boolean checkFailureClassForeignKeys(final List<String> cells) {
		if (cells.get(2).equalsIgnoreCase("(null)")) {
			failureClassRow = null;
			return true;
		} else {
			for (final FailureClass failureClass : failureClassData) {
				if (cells.get(2).equalsIgnoreCase(failureClass.getFailureClass())) {
					failureClassRow = failureClass;
					return true;
				}
			}
		}
		return false;

	}

	public boolean checkEventCauseForeignKeys(final List<String> cells) {
		if (cells.get(8).equalsIgnoreCase("(null)")) {
			for (final EventCause eventCause : eventCauseData) {
				if (cells.get(1).equalsIgnoreCase(eventCause.getId().getEventId())) {
					// placeholder for future datasets
					eventCauseRow = eventCause;
					return true;

				}
			}
		} else {
			for (final EventCause eventCause : eventCauseData) {
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

	public void setFailureClassData(final List<FailureClass> failureClassData) {
		this.failureClassData = failureClassData;
	}

//	public List<EventCause> getEventCauseData() {
//		return eventCauseData;
//	}

	public void setEventCauseData(final List<EventCause> eventCauseData) {
		this.eventCauseData = eventCauseData;
	}

//	public List<MccMnc> getMccMncData() {
//		return mccMncData;
//	}

	public void setMccMncData(final List<MccMnc> mccMncData) {
		this.mccMncData = mccMncData;
	}

//	public List<Ue> getUeData() {
//		return ueData;
//	}

	public void setUeData(final List<Ue> ueData) {
		this.ueData = ueData;
	}




}




