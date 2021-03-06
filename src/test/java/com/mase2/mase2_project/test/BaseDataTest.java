package com.mase2.mase2_project.test;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.mase2.mase2_project.model.EventCause;
import com.mase2.mase2_project.model.EventCausePK;
import com.mase2.mase2_project.model.FailureClass;
import com.mase2.mase2_project.model.MccMnc;
import com.mase2.mase2_project.model.MccMncPK;
import com.mase2.mase2_project.model.Ue;
import com.mase2.mase2_project.rest.BaseDataWS;
import com.mase2.mase2_project.rest.EventCauseWS;
import com.mase2.mase2_project.rest.JaxRsActivator;
import com.mase2.mase2_project.rest.MccMncWS;
import com.mase2.mase2_project.test.utils.UtilsDAO;
import com.mase2.mase2_project.util.DateParam;


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
						BaseDataDAO.class,BaseDataWS.class, 
						EventCause.class, EventCausePK.class,EventCauseDAO.class,
						DateParam.class,EventCauseWS.class, FailureClass.class, Ue.class,UeDAO.class)
			//	.addPackage(EventCause.class.getPackage())
			//	.addPackage(EventCauseDAO.class.getPackage())
						//this line will pick up the production db
				.addAsManifestResource("META-INF/persistence.xml",
						"persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");

	}
	
	 
	
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
	private UtilsDAO utilsDAO;
	
	private Ue ue;
	private FailureClass failureClass;
	private MccMnc mccMnc;
	private EventCause eventCause;
	
	
	@Before
	public void setUp() {
		utilsDAO.deleteTableBaseData();
		final BaseData baseData = new BaseData();
		baseData.setDateTime(new Date());
		baseData.setCellId("123");
		baseData.setDuration(12);
		baseData.setHier321Id("111111111");
		baseData.setHier32Id("22222222");
		baseData.setHier3Id("333333333");
		baseData.setImsi("1232456");
		baseData.setNeVersion("joe");
		utilsDAO.deleteTable();
		final MccMncPK mccMncPK = new MccMncPK();
		mccMncPK.setMcc("238");
		mccMncPK.setMnc("1");
		mccMnc=new MccMnc();
		mccMnc.setId(mccMncPK);
		mccMnc.setCountry("Denmark");
		mccMnc.setOperator("TDC-DK");
		mcc_mncDAO.save(mccMnc);
		baseData.setMccMnc(mccMnc);
		utilsDAO.deleteTableEventCause();
		final EventCausePK eventCausePK = new EventCausePK();
		eventCausePK.setEventId("4098");
		eventCausePK.setEventCode("1");
		eventCausePK.setEventId("4");
		eventCausePK.setEventCode("3");
		eventCause=new EventCause();
		eventCause.setId(eventCausePK);
		eventCause.setDescription("S1 SIG CONN SETUP-S1 INTERFACE DOWN");
		eventCauseDAO.save(eventCause);
		baseData.setEventCause(eventCause);
		utilsDAO.deleteTableUe();
		ue=new Ue();
        ue.setTac("100100");
        ue.setMarketingName("G410");
        ue.setManufacturer("Mitsubishi");
        ue.setAccessCapability("GSM 1800, GSM 900"); 
        ue.setTac("1");
        ue.setMarketingName("test");
        ue.setManufacturer("test");
        ue.setAccessCapability("test"); 
        ueDAO.save(ue);
		baseData.setUe(ue);
		utilsDAO.deleteTableFailureClass();
		failureClass=new FailureClass();
		failureClass.setFailureClass("2");
		failureClass.setDescription("MT ACCESS");
		baseData.setFailureClassBean(failureClass);
		failureClassDAO.save(failureClass);
		baseDataDao.save(baseData);
	}
	
	@Test
	public void testGetAllBaseData() {
		final List<BaseData> baseDataList = baseDataDao.getAllBaseData();
		assertEquals("Data fetch = data persisted", baseDataList.size(), 1);
	}
	
	@Test
	public void testCreateRowException() {
		final BaseData baseData = new BaseData();
		final ArrayList<String> cells = new ArrayList<String>();
		cells.add("test date parse exception");
		cells.add("test date parse exception");
		cells.add("test date parse exception");
		cells.add("test date parse exception");
		cells.add("test date parse exception");
		cells.add("test date parse exception");
		cells.add("11");
		cells.add("1000");
		cells.add("");
		cells.add("12a");
		cells.add("1111");
		cells.add("1111");
		cells.add("1111");
		cells.add("1111");
		
		baseData.createRow(cells, eventCause, failureClass, ue, mccMnc);
		assertEquals(new SimpleDateFormat("dd/MM/yy").format(baseData.getDateTime()),new SimpleDateFormat("dd/MM/yy").format(new Date()));
	}

}
