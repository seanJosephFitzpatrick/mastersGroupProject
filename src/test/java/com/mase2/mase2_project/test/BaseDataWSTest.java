package com.mase2.mase2_project.test;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.core.HttpHeaders;
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
import com.mase2.mase2_project.data.ExcelDAO;
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
import com.mase2.mase2_project.rest.FailureClassWS;
import com.mase2.mase2_project.rest.ImportWS;
import com.mase2.mase2_project.rest.JaxRsActivator;
import com.mase2.mase2_project.rest.MccMncWS;
import com.mase2.mase2_project.rest.UeWS;
import com.mase2.mase2_project.test.utils.UtilsDAO;
import com.mase2.mase2_project.util.DateParam;
import com.mase2.mase2_project.util.FileLogger;
import com.mase2.mase2_project.util.InvalidEntity;
import com.mase2.mase2_project.util.TableClearer;
import com.mase2.mase2_project.util.Validator;

import jxl.read.biff.BiffException;

@RunWith(Arquillian.class)
public class BaseDataWSTest {

	@Deployment
	public static Archive<?> createTestArchive() {

		return ShrinkWrap.create(JavaArchive.class, "BaseDataTest.jar")
				.addClasses(MccMncDAO.class, MccMnc.class, MccMncPK.class, JaxRsActivator.class, MccMncWS.class,
						UtilsDAO.class, FailureClassDAO.class, BaseData.class, BaseDataDAO.class, BaseDataWS.class,
						UeWS.class, EventCause.class, EventCausePK.class, EventCauseDAO.class, FailureClassWS.class,
						EventCauseWS.class, DateParam.class, FailureClass.class, ExcelDAO.class, InvalidEntity.class,
						FileLogger.class, Validator.class, Ue.class, UeDAO.class, ImportWS.class, TableClearer.class,
						java.util.Date.class)
				.addPackages(true, jxl.Sheet.class.getPackage()).addPackages(true, jxl.Workbook.class.getPackage())
				.addPackages(true, jxl.Cell.class.getPackage())
				.addPackages(true, jxl.biff.BaseCellFeatures.class.getPackage())
				.addPackages(true, jxl.HeaderFooter.Contents.class.getPackage())
				.addPackages(true, jxl.HeaderFooter.class.getPackage())
				.addPackages(true, jxl.biff.FontRecord.class.getPackage())
				.addPackages(true, jxl.format.Font.class.getPackage())
				.addPackages(true, jxl.write.WritableCell.class.getPackage())
				.addPackages(true, jxl.write.WritableHyperlink.class.getPackage())
				.addPackages(true, jxl.write.biff.HyperlinkRecord.class.getPackage())
				.addPackages(true, jxl.read.biff.CellValue.class.getPackage())
				.addPackages(true, jxl.read.biff.BaseSharedFormulaRecord.class.getPackage())
				.addPackages(true, common.Logger.class.getPackage())
				.addPackages(true, common.log.SimpleLogger.class.getPackage())
				// .addPackage(EventCause.class.getPackage())
				// .addPackage(EventCauseDAO.class.getPackage())
				// this line will pick up the production db
				.addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");

	}

	@EJB
	private BaseDataWS baseDataEndpoint;
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
	private ExcelDAO excelDAO;
	@EJB
	private UeWS ueWS;
	@EJB
	private MccMncWS mcc_mncWS;
	@EJB
	private EventCauseWS eventCauseEndpoint;
	@EJB
	private UtilsDAO utilsDAO;
	@EJB
	private ImportWS importWS;

	private static Calendar calendar;
	private static EventCause eventCause;
	private static Ue ue;
	private static MccMnc mccMnc;
	private static FailureClass failureClass;
	private static HttpHeaders httpHeaders;
	private static BaseData baseData;

	@Before
	public void setUp() {
		utilsDAO.deleteTableBaseData();

		final BaseData baseData = new BaseData();
		calendar = Calendar.getInstance();

		calendar.setTime(new Date());
		baseData.setDateTime(calendar.getTime());
		baseData.setCellId("4");
		baseData.setDuration(1000);
		baseData.setHier321Id("1150480000");
		baseData.setHier32Id("822680000");
		baseData.setHier3Id("4809000");
		baseData.setImsi("344930011");
		baseData.setNeVersion("11B");
		utilsDAO.deleteTable();
		final MccMncPK mccMncPK = new MccMncPK();
		mccMncPK.setMcc("238");
		mccMncPK.setMnc("1");
		mccMnc = new MccMnc();
		mccMnc.setId(mccMncPK);
		mccMnc.setCountry("Denmark");
		mccMnc.setOperator("TDC-DK");
		mcc_mncDAO.save(mccMnc);
		baseData.setMccMnc(mccMnc);
		utilsDAO.deleteTableEventCause();
		final EventCausePK eventCausePK = new EventCausePK();
		eventCausePK.setEventId("4097");
		eventCausePK.setEventCode("3");
		eventCause = new EventCause();
		eventCause.setId(eventCausePK);
		eventCause.setDescription("S1 SIG CONN SETUP-S1 INTERFACE DOWN");
		baseData.setEventCause(eventCause);
		eventCauseDAO.save(eventCause);
		utilsDAO.deleteTableUe();
		ue = new Ue();
		ue.setTac("100100");
		ue.setMarketingName("G410");
		ue.setManufacturer("Mitsubishi");
		ue.setAccessCapability("GSM 1800, GSM 900");
		ue.setModel("Apple");
		ueDAO.save(ue);
		baseData.setUe(ue);
		utilsDAO.deleteTableFailureClass();
		failureClass = new FailureClass();
		failureClass.setFailureClass("2");
		failureClass.setDescription("MT ACCESS");
		baseData.setFailureClassBean(failureClass);
		failureClassDAO.save(failureClass);
		baseDataDao.save(baseData);
		httpHeaders = utilsDAO.getHttpHeaders();
	}

	@Test
	public void testGetAllBaseData() {
		final Response responseBaseData = baseDataEndpoint.listAll(httpHeaders);
		@SuppressWarnings("unchecked")
		List<BaseData> baseDataList = (List<BaseData>) responseBaseData.getEntity();
		assertEquals(HttpStatus.SC_OK, responseBaseData.getStatus());
		assertEquals("Data fetch = data persisted", baseDataList.size(), 1);
		final BaseData baseData = baseDataList.get(0);
		assertEquals("11B", baseData.getNeVersion());
		assertEquals(new SimpleDateFormat("dd/MM/yy HH:mm").format(calendar.getTime()),
				new SimpleDateFormat("dd/MM/yy HH:mm").format(baseData.getDateTime()));
		assertEquals("4", baseData.getCellId());
		assertEquals("1150480000", baseData.getHier321Id());
		assertEquals("822680000", baseData.getHier32Id());
		assertEquals("4809000", baseData.getHier3Id());
		assertEquals("344930011", baseData.getImsi());
		assertEquals(1000, baseData.getDuration());

	}

	@Test
	public void testFindByIMSI() {
		final Response response = baseDataEndpoint.findByImsi(httpHeaders, "344930011");
		@SuppressWarnings("unchecked")
		List<EventCause> baseDataList = (List<EventCause>) response.getEntity();
		assertEquals(HttpStatus.SC_OK, response.getStatus());
		assertEquals("Data fetch = data persisted", baseDataList.size(), 1);
		final EventCause eventCause = baseDataList.get(0);
		assertEquals("4097", eventCause.getId().getEventId());
		assertEquals("3", eventCause.getId().getEventCode());

	}

	@Test
	public void testFindAllIMSIWithFailures() {
		final Response response = baseDataEndpoint.findByAllImsiWithFailures(httpHeaders, new DateParam("2018-02-12"),
				new DateParam("2019-02-12"));
		@SuppressWarnings("unchecked")
		List<String> baseDataList = (List<String>) response.getEntity();
		assertEquals(HttpStatus.SC_OK, response.getStatus());
		assertEquals("Data fetch = data persisted", baseDataList.size(), 1);
		final String imsi = baseDataList.get(0);
		assertEquals("344930011", imsi);

	}

	@Test
	public void testFindByCellIdAndDateTime() {
		final Response response = baseDataEndpoint.findByCellIdAndDateTime(httpHeaders, "Apple",
				new DateParam("2018-02-12"), new DateParam("2019-02-12"));
		@SuppressWarnings("unchecked")
		List<Long> baseDataList = (List<Long>) response.getEntity();
		assertEquals(HttpStatus.SC_OK, response.getStatus());
		assertEquals("Data fetch = data persisted", baseDataList.size(), 1);
		final long count = baseDataList.get(0);
		assertEquals(1, count);

	}

	@Test
	public void testCountForIMSIFailureDate() {
		final Response response = baseDataEndpoint.findByIMSIAndDateTime(httpHeaders, "344930011",new DateParam("2011-03-08"), new DateParam("2019-03-05"));
		List<Long> baseDataList = (List<Long>) response.getEntity();
		assertEquals(HttpStatus.SC_OK, response.getStatus());				
		assertEquals("Data fetch = data persisted", baseDataList.size(), 1);
		final long count = baseDataList.get(0);
		assertEquals(1, count);	
				
	}
	@Test
	public void testFindByDateTime() {
		final Response response = baseDataEndpoint.findByDateTime(httpHeaders, new DateParam("2018-02-12"),
				new DateParam("2019-02-12"));
		List baseDataList = (List) response.getEntity();
		assertEquals(HttpStatus.SC_OK, response.getStatus());
		assertEquals("Data fetch = data persisted", baseDataList.size(), 1);
	}

	@Test
	public void testFindUniqueEventIdCauseCodeByModel() {
		final Response response = baseDataEndpoint.findByUniqueModelCombinations(httpHeaders, "Apple");
		@SuppressWarnings("unchecked")
		List<EventCause> baseDataList = (List<EventCause>) response.getEntity();
		assertEquals(HttpStatus.SC_OK, response.getStatus());
		assertEquals("Data fetch = data persisted", baseDataList.size(), 1);
		assertEquals("4097", eventCause.getId().getEventId());
		assertEquals("3", eventCause.getId().getEventCode());

	}

	@Test
	public void testGetAllEventCauses() {
		final Response response = eventCauseEndpoint.listAll(httpHeaders);
		List<EventCause> eventCauseList = (List<EventCause>) response.getEntity();
		assertEquals(HttpStatus.SC_OK, response.getStatus());
		assertEquals("Data fetch = data persisted", eventCauseList.size(), 1);
		final EventCause eventCause = eventCauseList.get(0);
		assertEquals("4097", eventCause.getId().getEventId());
		assertEquals("3", eventCause.getId().getEventCode());
		assertEquals("S1 SIG CONN SETUP-S1 INTERFACE DOWN", eventCause.getDescription());
	}

	@Test
	public void testGetAllMccMncs() {
		final Response response = mcc_mncWS.findAllMccMncs(httpHeaders);
		List<MccMnc> mccMncList = (List<MccMnc>) response.getEntity();
		assertEquals(HttpStatus.SC_OK, response.getStatus());
		assertEquals("Data fetch = data persisted", mccMncList.size(), 1);
		final MccMnc mccMnc = mccMncList.get(0);
		assertEquals("238", mccMnc.getId().getMcc());
		assertEquals("1", mccMnc.getId().getMnc());
		assertEquals("Denmark", mccMnc.getCountry());
		assertEquals("TDC-DK", mccMnc.getOperator());
	}

	@Test
	public void testGetAllUes() {
		final Response response = ueWS.findAllUes(httpHeaders);
		List<Ue> uEList = (List<Ue>) response.getEntity();
		assertEquals(HttpStatus.SC_OK, response.getStatus());
		assertEquals("Data fetch = data persisted", uEList.size(), 1);
		final Ue uE = uEList.get(0);
		assertEquals("100100", uE.getTac());
		assertEquals("G410", uE.getMarketingName());
		assertEquals("Mitsubishi", uE.getManufacturer());
		assertEquals("GSM 1800, GSM 900", uE.getAccessCapability());
	}

	@Test
	public void testGetAllFailureClassWS() {
		final Response response = failureClassWS.findAllFailureClasses(httpHeaders);
		List<FailureClass> failureClassList = (List<FailureClass>) response.getEntity();
		assertEquals(HttpStatus.SC_OK, response.getStatus());
		assertEquals("Data fetch = data persisted", failureClassList.size(), 1);
		final FailureClass failureClass = failureClassList.get(0);
		assertEquals("2", failureClass.getFailureClass());
		assertEquals("MT ACCESS", failureClass.getDescription());
	}

	@Test
	public void testFindTopTenFailuresByDateTime() {
		final Response response = baseDataEndpoint.findTopTenFailuresByDateTime(httpHeaders, new DateParam("2010-03-07"),
				new DateParam("2019-03-09"));
		List baseDataList = (List) response.getEntity();
		assertEquals(HttpStatus.SC_OK, response.getStatus());
		assertEquals("Data fetch = data persisted", 1, baseDataList.size());
	}

}
