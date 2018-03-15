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
import com.mase2.mase2_project.util.Validator;

public class ValidatorTest {

	@Test
	public void testMccMncValidData() {
		final MccMnc mccMnc = new MccMnc();
		final MccMncPK mccMncPK = new MccMncPK();
		mccMnc.setCountry("Denmark");
		mccMnc.setOperator("TDK");
		mccMncPK.setMcc("123");
		mccMncPK.setMnc("456");
		mccMnc.setId(mccMncPK);
		assertTrue(Validator.validateMcc_Mnc(mccMnc));
	}
	
	@Test
	public void testMccMncInvalidData() {
		final MccMnc mccMnc = new MccMnc();
		final MccMncPK mccMncPK = new MccMncPK();
		mccMnc.setCountry("123");
		mccMnc.setOperator("TDK");
		mccMncPK.setMcc("123");
		mccMncPK.setMnc("456");
		mccMnc.setId(mccMncPK);
		assertFalse(Validator.validateMcc_Mnc(mccMnc));
	}
	
	@Test
	public void testEventCauseValidData() {
		final EventCause eventCause = new EventCause();
		final EventCausePK eventCausePk = new EventCausePK();
		eventCause.setDescription("RRC CONN SETUP-SUCCESS");
		eventCausePk.setEventCode("7");
		eventCausePk.setEventId("4097");
		eventCause.setId(eventCausePk);
		assertTrue(Validator.validateEventCause(eventCause));
	}
	
	@Test
	public void testEventCauseInvalidData() {
		final EventCause eventCause = new EventCause();
		final EventCausePK eventCausePk = new EventCausePK();
		eventCause.setDescription("RRC CONN SETUP-SUCCESS");
		eventCausePk.setEventCode("7");
		eventCausePk.setEventId("text");
		eventCause.setId(eventCausePk);
		assertFalse(Validator.validateEventCause(eventCause));
	}
	
	@Test
	public void testFailureClassValidData() {
		final FailureClass failureClass = new FailureClass();
		failureClass.setDescription("EMERGENCY");
		failureClass.setFailureClass("3");
		assertTrue(Validator.validateFailureClass(failureClass));
	}
	
	@Test
	public void testFailureClassInvalidData() {
		final FailureClass failureClass = new FailureClass();
		failureClass.setDescription("EMERGENCY");
		failureClass.setFailureClass("5");
		assertFalse(Validator.validateFailureClass(failureClass));
	}
	
	@Test
	public void testUeValidData() {
		final Ue ue = new Ue();
		ue.setTac("100100");
		ue.setMarketingName("G410");
		ue.setManufacturer("Mitsubishi");
		ue.setAccessCapability("GSM 1800");
		ue.setModel("G410");
		ue.setVendorName("Mitsubishi");
		ue.setUeType("HANDHELD");
		ue.setOs("BLACKBERRY");
		ue.setInputType("QWERTY");
		assertTrue(Validator.validateUe(ue));
	}
	
	@Test
	public void testUeInvalidData() {
		final Ue ue = new Ue();
		ue.setTac("Wrong");
		ue.setMarketingName("G41%%0");
		ue.setManufacturer("Mitsu*&bishi");
		ue.setAccessCapability("GSM 1800..");
		ue.setModel("G410");
		ue.setVendorName("Mitsubishi");
		ue.setUeType("HANDHELD");
		ue.setOs("BLACKBERRY");
		ue.setInputType("012345");
		assertFalse(Validator.validateUe(ue));
	}

}
