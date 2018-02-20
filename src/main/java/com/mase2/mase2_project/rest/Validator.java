package com.mase2.mase2_project.rest;

import com.mase2.mase2_project.model.EventCause;
import com.mase2.mase2_project.model.MccMnc;

public class Validator {

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
	
	
	}




