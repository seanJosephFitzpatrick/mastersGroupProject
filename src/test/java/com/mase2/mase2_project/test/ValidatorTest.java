package com.mase2.mase2_project.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.mase2.mase2_project.model.EventCause;
import com.mase2.mase2_project.model.EventCausePK;
import com.mase2.mase2_project.model.FailureClass;
import com.mase2.mase2_project.model.MccMnc;
import com.mase2.mase2_project.model.MccMncPK;
import com.mase2.mase2_project.model.Ue;
import com.mase2.mase2_project.rest.Validator;

public class ValidatorTest {

	@Test
	public void testMccMncValidData() {
		MccMnc mccMnc = new MccMnc();
		MccMncPK mccMncPK = new MccMncPK();
		Validator validator = new Validator();
		mccMnc.setCountry("Denmark");
		mccMnc.setOperator("TDK");
		mccMncPK.setMcc("123");
		mccMncPK.setMnc("456");
		mccMnc.setId(mccMncPK);
		assertTrue(validator.validateMcc_Mnc(mccMnc));
	}
	
	@Test
	public void testMccMncInvalidData() {
		MccMnc mccMnc = new MccMnc();
		MccMncPK mccMncPK = new MccMncPK();
		Validator validator = new Validator();
		mccMnc.setCountry("123");
		mccMnc.setOperator("TDK");
		mccMncPK.setMcc("123");
		mccMncPK.setMnc("456");
		mccMnc.setId(mccMncPK);
		assertFalse(validator.validateMcc_Mnc(mccMnc));
	}
	
	@Test
	public void testEventCauseValidData() {
		EventCause eventCause = new EventCause();
		EventCausePK eventCausePk = new EventCausePK();
		Validator validator = new Validator();
		eventCause.setDescription("RRC CONN SETUP-SUCCESS");
		eventCausePk.setEventCode("7");
		eventCausePk.setEventId("4097");
		eventCause.setId(eventCausePk);
		assertTrue(validator.validateEventCause(eventCause));
	}
	
	@Test
	public void testEventCauseInvalidData() {
		EventCause eventCause = new EventCause();
		EventCausePK eventCausePk = new EventCausePK();
		Validator validator = new Validator();
		eventCause.setDescription("RRC CONN SETUP-SUCCESS");
		eventCausePk.setEventCode("7");
		eventCausePk.setEventId("text");
		eventCause.setId(eventCausePk);
		assertFalse(validator.validateEventCause(eventCause));
	}
	
	@Test
	public void testFailureClassValidData() {
		FailureClass failureClass = new FailureClass();
		Validator validator = new Validator();
		failureClass.setDescription("EMERGENCY");
		failureClass.setFailureClass("3");
		assertTrue(validator.validateFailureClass(failureClass));
	}
	
	@Test
	public void testFailureClassInvalidData() {
		FailureClass failureClass = new FailureClass();
		Validator validator = new Validator();
		failureClass.setDescription("EMERGENCY");
		failureClass.setFailureClass("5");
		assertFalse(validator.validateFailureClass(failureClass));
	}
	
	@Test
	public void testUeValidData() {
		Ue ue = new Ue();
		Validator validator = new Validator();
		ue.setTac("100100");
		ue.setMarketingName("G410");
		ue.setManufacturer("Mitsubishi");
		ue.setAccessCapability("GSM 1800");
		ue.setModel("G410");
		ue.setVendorName("Mitsubishi");
		ue.setUeType("HANDHELD");
		ue.setOs("BLACKBERRY");
		ue.setInputType("QWERTY");
		assertTrue(validator.validateUe(ue));
	}
	
	@Test
	public void testUeInvalidData() {
		Ue ue = new Ue();
		Validator validator = new Validator();
		ue.setTac("Wrong");
		ue.setMarketingName("G41%%0");
		ue.setManufacturer("Mitsu*&bishi");
		ue.setAccessCapability("GSM 1800..");
		ue.setModel("G410");
		ue.setVendorName("Mitsubishi");
		ue.setUeType("HANDHELD");
		ue.setOs("BLACKBERRY");
		ue.setInputType("012345");
		assertFalse(validator.validateUe(ue));
	}

}
