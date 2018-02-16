package com.mase2.mase2_project.test;

import static org.junit.Assert.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.core.Response;
import org.apache.commons.httpclient.HttpStatus;
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
import com.mase2.mase2_project.rest.BaseDataEndpoint;
import com.mase2.mase2_project.rest.EventCauseEndpoint;
import com.mase2.mase2_project.rest.FailureClassWS;
import com.mase2.mase2_project.rest.JaxRsActivator;
import com.mase2.mase2_project.rest.MccMncWS;
import com.mase2.mase2_project.rest.UeWS;
import com.mase2.mase2_project.test.utils.UtilsDAO;


@RunWith(Arquillian.class)
public class BaseDataWSTest {

	@Deployment
	public static Archive<?> createTestArchive() {
		return ShrinkWrap
				.create(JavaArchive.class, "BaseDataTest.jar")
				.addClasses(MccMncDAO.class, MccMnc.class,
						MccMncPK.class,
						JaxRsActivator.class,MccMncWS.class,
						UtilsDAO.class, FailureClassDAO.class, BaseData.class, 
					    BaseDataDAO.class,BaseDataEndpoint.class, UeWS.class, 
						EventCause.class, EventCausePK.class,EventCauseDAO.class, FailureClassWS.class,
						EventCauseEndpoint.class, FailureClass.class, Ue.class,UeDAO.class)
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
	private FailureClassWS failureClassWS;
	@EJB
	private UeWS ueWS;
	@EJB
	private MccMncWS mcc_mncWS;
	@EJB
	private EventCauseEndpoint eventCauseEndpoint;
	@EJB
	private UtilsDAO utilsDAO;
	
	@Before
	public void setUp() {
		utilsDAO.deleteTableBaseData();
		BaseData baseData = new BaseData();
		baseData.setCellId(4);
		baseData.setDuration(1000);
		baseData.setHier321Id(new BigDecimal(1150480000));
		baseData.setHier32Id(new BigDecimal(822680000));
		baseData.setHier3Id(new BigDecimal(4809000));
		baseData.setImsi(new BigDecimal(344930011));
		baseData.setNeVersion("11B");
		utilsDAO.deleteTable();
		MccMncPK mccMncPK = new MccMncPK();
		mccMncPK.setMcc(238);
		mccMncPK.setMnc(1);
		MccMnc mccMnc=new MccMnc();
		mccMnc.setId(mccMncPK);
		mccMnc.setCountry("Denmark");
		mccMnc.setOperator("TDC-DK");
		mcc_mncDAO.save(mccMnc);
		baseData.setMccMnc(mccMnc);
		utilsDAO.deleteTableEventCause();
		EventCausePK eventCausePK = new EventCausePK();
		eventCausePK.setEventId(4097);
		eventCausePK.setEventCode(3);
		EventCause eventCause=new EventCause();
		eventCause.setId(eventCausePK);
		eventCause.setDescription("RRC CONN SETUP-EUTRAN GENERATED REASON");
		eventCauseDAO.save(eventCause);
		baseData.setEventCause(eventCause);
		utilsDAO.deleteTableUe();
		Ue ue=new Ue();
        ue.setTac(100100);
        ue.setMarketingName("G410");
        ue.setManufacturer("Mitsubishi");
        ue.setAccessCapability("GSM 1800, GSM 900");
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
	  	Response responseBaseData = baseDataEndpoint.listAll();
		List<BaseData> baseDataList = (List<BaseData>) responseBaseData.getEntity();
		assertEquals(HttpStatus.SC_OK, responseBaseData.getStatus());				
		assertEquals("Data fetch = data persisted", baseDataList.size(), 1);
		BaseData baseData = baseDataList.get(0);
		assertEquals("11B", baseData.getNeVersion());			
	}
	
	@Test
	public void testGetAllEventCauses() {
		Response response = eventCauseEndpoint.listAll();
		List<EventCause> eventCauseList = (List<EventCause>) response.getEntity();
		assertEquals(HttpStatus.SC_OK, response.getStatus());				
		assertEquals("Data fetch = data persisted", eventCauseList.size(), 1);
		EventCause eventCause = eventCauseList.get(0);
		assertEquals("RRC CONN SETUP-EUTRAN GENERATED REASON", eventCause.getDescription());	
	}
	
	@Test
	public void testGetAllMccMncs() {
		Response response = mcc_mncWS.findAllMccMncs();
		List<MccMnc> mccMncList = (List<MccMnc>) response.getEntity();
		assertEquals(HttpStatus.SC_OK, response.getStatus());				
		assertEquals("Data fetch = data persisted", mccMncList.size(), 1);
		MccMnc mccMnc = mccMncList.get(0);
		assertEquals("TDC-DK",mccMnc.getOperator());
	}
	
    @Test
    public void testGetAllUes() {
        Response response = ueWS.findAllUes();
		List<Ue> uEList = (List<Ue>) response.getEntity();
		assertEquals(HttpStatus.SC_OK, response.getStatus());				
		assertEquals("Data fetch = data persisted", uEList.size(), 1);
		Ue uE = uEList.get(0);
		assertEquals("Mitsubishi", uE.getManufacturer());
    }
	
	@Test
	public void testGetAllFailureClassWS() {
		Response response = failureClassWS.findAllFailureClasses();
		List<FailureClass> failureClassList = (List<FailureClass>) response.getEntity();
		assertEquals(HttpStatus.SC_OK, response.getStatus());				
		assertEquals("Data fetch = data persisted", failureClassList.size(), 1);
		FailureClass failureClass = failureClassList.get(0);
		assertEquals("MT ACCESS",failureClass.getDescription());
	}
	
}
