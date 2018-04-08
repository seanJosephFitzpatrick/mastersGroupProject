package com.mase2.mase2_project.test;

import static org.junit.Assert.assertEquals;

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
		public class UserTest {
			
			@Deployment
			public static Archive<?> createTestArchive() {
				return ShrinkWrap
						.create(JavaArchive.class, "TestFailureClass.jar")
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
						.addAsManifestResource("META-INF/persistence.xml",
								"persistence.xml")
						.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");

			}

			private User user;
			@EJB
			private UserDAO userDAO;
			
			@EJB
			private UtilsDAO utilsDAO;
			 
			@Before
			public void setUp() {
				//this function means that we start with an empty table
				//And add one Failure Class and description
				//it should be possible to test with an in memory db for efficiency
				utilsDAO.deleteUserTable();
				user = new User();
				user.setEmail("admin@mase2.ie");
				user.setPassword("202cb962ac59075b964b07152d234b70");
				user.setRole("admin");
				userDAO.save(user);
	
			}
			@Test
			public void testSetId() {
				user.setId(2);
				assertEquals(2, user.getId());
			}
			
			@Test
			public void testGetAllUsers() {
				final List<User> userList = userDAO.getAllUsers();
				assertEquals("Data fetch = data persisted", userList.size(), 1);
			}
			
			@Test
			public void testGeUsersByEmail() {
				final List<User> userList = userDAO.findByEmail("admin@mase2.ie");
				assertEquals("Data fetch = data persisted", userList.size(), 1);
			}
			
			@Test
			public void testGeUsersByID() {
				user.setEmail("admi2@mase2.ie");
				userDAO.update(user);
				final List<User> userList = userDAO.findByEmail("admi2@mase2.ie");
				assertEquals("Data fetch = data persisted", userList.size(), 1);
				assertEquals("Data fetch = data persisted", userList.get(0).getEmail(), "admi2@mase2.ie");
				user.setEmail("admin@mase2.ie");
				userDAO.update(user);
			}
}
