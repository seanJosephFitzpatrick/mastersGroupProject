package com.mase2.mase2_project.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.mase2.mase2_project.data.BaseDataDAO;
import com.mase2.mase2_project.data.EventCauseDAO;
import com.mase2.mase2_project.data.FailureClassDAO;
import com.mase2.mase2_project.data.MccMncDAO;
import com.mase2.mase2_project.data.UeDAO;
import com.mase2.mase2_project.model.BaseData;
import com.mase2.mase2_project.model.BaseDataPK;
import com.mase2.mase2_project.model.EventCause;
import com.mase2.mase2_project.model.EventCausePK;
import com.mase2.mase2_project.model.FailureClass;
import com.mase2.mase2_project.model.MccMnc;
import com.mase2.mase2_project.model.MccMncPK;
import com.mase2.mase2_project.model.Ue;
import com.mase2.mase2_project.rest.BaseDataEndpoint;
import com.mase2.mase2_project.rest.EventCauseEndpoint;
import com.mase2.mase2_project.rest.JaxRsActivator;
import com.mase2.mase2_project.rest.MccMncWS;
import com.mase2.mase2_project.test.utils.UeUtilsDAO;
import com.mase2.mase2_project.test.utils.UtilsDAO;


@RunWith(Arquillian.class)
public class BaseDataTest {

	@Deployment
	public static Archive<?> createTestArchive() {
		return ShrinkWrap
				.create(JavaArchive.class, "BaseDataTest.jar")
				.addClasses(MccMncDAO.class, MccMnc.class,
						MccMncPK.class,
						JaxRsActivator.class,MccMncWS.class,
						UtilsDAO.class, FailureClassDAO.class, BaseData.class, 
						BaseDataPK.class, BaseDataDAO.class,BaseDataEndpoint.class, 
						EventCause.class, EventCausePK.class,EventCauseDAO.class,
						EventCauseEndpoint.class, FailureClass.class, Ue.class,UeDAO.class,UeUtilsDAO.class)
			//	.addPackage(EventCause.class.getPackage())
			//	.addPackage(EventCauseDAO.class.getPackage())
						//this line will pick up the production db
				.addAsManifestResource("META-INF/persistence.xml",
						"persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");

	}
	
	 
	@EJB
	private BaseDataEndpoint baseDataEndpoint;
	
	@EJB
	private BaseDataDAO baseDataDao;
	@EJB
	private FailureClassDAO failureClassDAO;
	@EJB
	private EventCauseDAO eventCauseDAO;
	@EJB
	private MccMncDAO mcc_mncDAO;
	@EJB
    private UeDAO ueDAO;
	@EJB
    private UeUtilsDAO ueUtilsDAO;
	
	@EJB
	private UtilsDAO utilsDAO;
	
	@Before
	public void setUp() {
		utilsDAO.deleteTableBaseData();
		BaseDataPK baseDataPK = new BaseDataPK();
		baseDataPK.setCauseCode(3);
		baseDataPK.setEventId(4);
		BaseData baseData = new BaseData();
		baseData.setId(baseDataPK);
		baseData.setCellId(123);
		baseData.setDuration(12);
		baseData.setHier321Id(new BigDecimal(111111111));
		baseData.setHier32Id(new BigDecimal(22222222));
		baseData.setHier3Id(new BigDecimal(333333333));
		baseData.setImsi(new BigDecimal(1232456));
		baseData.setNeVersion("joe");
		utilsDAO.deleteTable();
		MccMncPK mccMncPK = new MccMncPK();
		mccMncPK.setMcc(238);
		mccMncPK.setMnc(1);
		MccMnc mccMnc=new MccMnc();
		mccMnc.setId(mccMncPK);
		mccMnc.setCountry("Sweden");
		mccMnc.setOperator("TDC-DK");
		mcc_mncDAO.save(mccMnc);
		baseData.setMccMnc(mccMnc);
		utilsDAO.deleteTableEventCause();
		EventCausePK eventCausePK = new EventCausePK();
		eventCausePK.setEventId(4);
		eventCausePK.setEventCode(3);
		EventCause eventCause=new EventCause();
		eventCause.setId(eventCausePK);
		eventCause.setDescription("RRC CONN SETUP-EUTRAN GENERATED REASON");
		eventCauseDAO.save(eventCause);
		baseData.setEventCause(eventCause);
		ueUtilsDAO.deleteTable();
		Ue ue=new Ue();
        ue.setTac(1);
        ue.setMarketingName("test");
        ue.setManufacturer("test");
        ue.setAccessCapability("test"); 
        ueDAO.save(ue);
		baseData.setUe(ue);
		utilsDAO.deleteTableFailureClass();
		FailureClass failureClass=new FailureClass();
		failureClass.setFailureClass(2);
		failureClass.setDescription("MT ACCESS");
		baseData.setFailureClassBean(failureClass);
		failureClassDAO.save(failureClass);
		eventCause.setBaseData(new ArrayList<BaseData>());
		eventCause.addBaseData(baseData);
		baseDataDao.save(baseData);
	}
	
	@Test
	public void testGetAllBaseData() {
		List<BaseData> baseDataList = baseDataDao.getAllBaseData();
		assertEquals("Data fetch = data persisted", baseDataList.size(), 1);
	}

	
}