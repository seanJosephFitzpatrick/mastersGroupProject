package com.mase2.mase2_project.test;

import static org.junit.Assert.assertEquals;

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
import com.mase2.mase2_project.data.FileNameDAO;
import com.mase2.mase2_project.data.MccMncDAO;
import com.mase2.mase2_project.data.UeDAO;
import com.mase2.mase2_project.data.UserDAO;
import com.mase2.mase2_project.graph_model.ImsiNode;
import com.mase2.mase2_project.model.BaseData;
import com.mase2.mase2_project.model.EventCause;
import com.mase2.mase2_project.model.EventCausePK;
import com.mase2.mase2_project.model.FailureClass;
import com.mase2.mase2_project.model.MccMnc;
import com.mase2.mase2_project.model.MccMncPK;
import com.mase2.mase2_project.model.Ue;
import com.mase2.mase2_project.model.User;
import com.mase2.mase2_project.rest.BaseDataWS;
import com.mase2.mase2_project.rest.EventCauseWS;
import com.mase2.mase2_project.rest.FailureClassWS;
import com.mase2.mase2_project.rest.ImportWS;
import com.mase2.mase2_project.rest.JaxRsActivator;
import com.mase2.mase2_project.rest.MccMncWS;
import com.mase2.mase2_project.rest.UeWS;
import com.mase2.mase2_project.rest.UserWS;
import com.mase2.mase2_project.test.utils.UtilsDAO;
import com.mase2.mase2_project.util.AutoComObject;
import com.mase2.mase2_project.util.DateParam;
import com.mase2.mase2_project.util.DurationAndCountObject;
import com.mase2.mase2_project.util.FailureCountObject;
import com.mase2.mase2_project.util.FileLogger;
import com.mase2.mase2_project.util.FileSystemMonitor;
import com.mase2.mase2_project.util.IMSIObject;
import com.mase2.mase2_project.util.InvalidEntity;
import com.mase2.mase2_project.util.SecurityCheck;
import com.mase2.mase2_project.util.TableClearer;
import com.mase2.mase2_project.util.TopTenFailuresObject;
import com.mase2.mase2_project.util.UniqueEventAndCauseObject;
import com.mase2.mase2_project.util.Validator;

@RunWith(Arquillian.class)
public class UserWSTest {

	@Deployment
	public static Archive<?> createTestArchive() {
		return ShrinkWrap.create(JavaArchive.class, "TestFailureClassWS.jar")
				.addClasses(FileNameDAO.class,FileSystemMonitor.class, MccMncDAO.class, MccMnc.class, MccMncPK.class, JaxRsActivator.class, MccMncWS.class,
						UtilsDAO.class, FailureClassDAO.class, BaseData.class, BaseDataDAO.class, BaseDataWS.class,
						UeWS.class, EventCause.class,TopTenFailuresObject.class,FailureCountObject.class, EventCausePK.class, EventCauseDAO.class, FailureClassWS.class,
						EventCauseWS.class, User.class,UserWS.class, DateParam.class, FailureClass.class, ExcelDAO.class, InvalidEntity.class,
						FileLogger.class,UniqueEventAndCauseObject.class, Validator.class,DurationAndCountObject.class,IMSIObject.class, UserDAO.class, SecurityCheck.class, Ue.class, UeDAO.class, ImportWS.class, TableClearer.class,
						java.util.Date.class)
				.addPackage(EventCause.class.getPackage())
				.addPackage(EventCauseDAO.class.getPackage())
				.addPackage(EventCauseWS.class.getPackage())
				.addPackage(ImsiNode.class.getPackage())
				.addPackage(AutoComObject.class.getPackage())
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
				.addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");

	}

	@EJB
	private UserDAO userDAO;
	@EJB
	private UserWS userWS;
	@EJB
	private UtilsDAO utilsDAO;

	private static HttpHeaders httpHeaders;

	private User user;
	
	@Before
	public void setUp() {
		// this function means that we start with an empty table
		// And add one Failure Class and description
		// it should be possible to test with an in memory db for efficiency
		utilsDAO.deleteUserTable();
		user = new User();
		user.setEmail("admin@mase2.ie");
		user.setPassword("202cb962ac59075b964b07152d234b70");
		user.setRole("admin");
		userDAO.save(user);
		httpHeaders = utilsDAO.getHttpHeaders();
	}
	@Test
	public void testFindByEmailUsersWS() {
		
		final Response response = userWS.findByEmail(httpHeaders, "admin@mase2.ie");
		List<User> userList = (List<User>) response.getEntity();
		assertEquals(userList.size(), 1);
		assertEquals(HttpStatus.SC_OK, response.getStatus());
		
	}
	
	@Test
	public void testUpdateUsersWS() {
		user.setEmail("admin@mase2.ie");
		final Response response = userWS.update(httpHeaders,1l, user);
//		List<User> userList = (List<User>) response.getEntity();
//		assertEquals(userList.size(), 2);
		assertEquals(HttpStatus.SC_OK, response.getStatus());
		
	}

	@Test
	public void testCreateUsersWS() {
		User userTest = new User();
		userTest.setEmail("admin2@mase2.ie");
		userTest.setPassword("5777f605aff3362ca976c1ca0dffffe4");
		userTest.setRole("admin");
		final Response response = userWS.create(httpHeaders, userTest);
//		List<User> userList = (List<User>) response.getEntity();
//		assertEquals(userList.size(), 2);
		assertEquals(HttpStatus.SC_OK, response.getStatus());
		
	}
	
	@Test
	public void testLoginUsersWS() {
		final Response response = userWS.login(httpHeaders);
		assertEquals(HttpStatus.SC_OK, response.getStatus());
	}
	
	@Test
	public void testGetAllUsersWS() {
		final Response response = userWS.listAll(httpHeaders);
		@SuppressWarnings("unchecked")
		List<User> userList = (List<User>) response.getEntity();
		assertEquals(HttpStatus.SC_OK, response.getStatus());
		
		assertEquals("Data fetch = data persisted", userList.size(), 1);
		final User userTest = userList.get(0);
		assertEquals("admin@mase2.ie", userTest.getEmail());
		
		assertEquals("5777f605aff3362ca976c1ca0dffffe4", userTest.getPassword());
		assertEquals("admin", userTest.getRole());

	}
}
