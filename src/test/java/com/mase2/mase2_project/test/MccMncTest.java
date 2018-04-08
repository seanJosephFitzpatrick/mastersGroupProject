package com.mase2.mase2_project.test;

import static org.junit.Assert.*;

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
		public class MccMncTest {
			
			@Deployment
			public static Archive<?> createTestArchive() {
				return ShrinkWrap
						.create(JavaArchive.class, "Testing.jar")
						.addClasses(FileNameDAO.class,FileSystemMonitor.class,MccMncDAO.class, MccMnc.class, MccMncPK.class, JaxRsActivator.class, MccMncWS.class,
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

			 
			
			@EJB
			private MccMncDAO mcc_mncDAO;
			
			@EJB
			private UtilsDAO utilsDAO;
			
			private MccMnc mccMnc;
			 
			@Before
			public void setUp() {
				utilsDAO.deleteTableBaseData();
				utilsDAO.deleteTable();
				final MccMncPK mccMncPK = new MccMncPK();
				mccMncPK.setMcc("238");
				mccMncPK.setMnc("1");
				mccMnc=new MccMnc();
				mccMnc.setId(mccMncPK);
				mccMnc.setCountry("Sweden");
				mccMnc.setOperator("TDC-DK");
				mcc_mncDAO.save(mccMnc);
			}
			
			@Test
			public void testGetAllMccMncs() {
				final List<MccMnc> mccMncList = mcc_mncDAO.getAllMcc_Mncs();
				assertEquals("Data fetch = data persisted", mccMncList.size(), 1);
			}
			@Test
			public void testMccMncPKEqual(){
				final MccMncPK mccMncPK = new MccMncPK();
				mccMncPK.setMcc("238");
				mccMncPK.setMnc("1");
				assertTrue(mccMncPK.equals(mccMnc.getId()));
				assertTrue(mccMncPK.equals(mccMncPK));
				
			}
			@Test
			public void testMccMncPKUnequal(){
				final MccMncPK mccMncPK = new MccMncPK();
				mccMncPK.setMcc("237");
				mccMncPK.setMnc("1");
				assertFalse(mccMncPK.equals(mccMnc.getId()));
				final String test="";
				assertFalse(mccMncPK.equals(test));
				
			}
			
			
			
}
