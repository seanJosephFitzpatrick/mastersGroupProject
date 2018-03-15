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
import com.mase2.mase2_project.data.MccMncDAO;
import com.mase2.mase2_project.data.UeDAO;
import com.mase2.mase2_project.data.UserDAO;
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
import com.mase2.mase2_project.util.DateParam;
import com.mase2.mase2_project.util.DurationAndCountObject;
import com.mase2.mase2_project.util.FailureCountObject;
import com.mase2.mase2_project.util.FileLogger;
import com.mase2.mase2_project.util.IMSIObject;
import com.mase2.mase2_project.util.InvalidEntity;
import com.mase2.mase2_project.util.SecurityCheck;
import com.mase2.mase2_project.util.TableClearer;
import com.mase2.mase2_project.util.TopTenFailuresObject;
import com.mase2.mase2_project.util.UniqueEventAndCauseObject;
import com.mase2.mase2_project.util.Validator;


    //    @FixMethodOrder(MethodSorters.NAME_ASCENDING)
        @RunWith(Arquillian.class)
        public class UeTest {
            
            @Deployment
            public static Archive<?> createTestArchive() {
                return ShrinkWrap
                        .create(JavaArchive.class, "Test.jar")
                        .addClasses(MccMncDAO.class, MccMnc.class, MccMncPK.class, JaxRsActivator.class, MccMncWS.class,
        						UtilsDAO.class, FailureClassDAO.class, BaseData.class, BaseDataDAO.class, BaseDataWS.class,
        						UeWS.class, EventCause.class,TopTenFailuresObject.class,FailureCountObject.class, EventCausePK.class, EventCauseDAO.class, FailureClassWS.class,
        						EventCauseWS.class, User.class,UserWS.class, DateParam.class, FailureClass.class, ExcelDAO.class, InvalidEntity.class,
        						FileLogger.class,UniqueEventAndCauseObject.class, Validator.class,DurationAndCountObject.class,IMSIObject.class, UserDAO.class, SecurityCheck.class, Ue.class, UeDAO.class, ImportWS.class, TableClearer.class,
        						java.util.Date.class)
                    //    .addPackage(EventCause.class.getPackage())
                    //    .addPackage(EventCauseDAO.class.getPackage())
                // this line will pick up the production db
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
            private com.mase2.mase2_project.data.UeDAO ueDAO;
            
            @EJB
            private UtilsDAO utilsDao;
             
            @Before
            public void setUp() {
                //this function means that we start with an empty table
                //And add one wine
                //it should be possible to test with an in memory db for efficiency
            	utilsDao.deleteTableBaseData();
            	utilsDao.deleteTableUe();
            	final Ue ue=new Ue();
                ue.setTac("100100");
                ue.setMarketingName("G410");
                ue.setManufacturer("Mitsubishi");
                ue.setAccessCapability("GSM 1800, GSM 900");
                ueDAO.save(ue);
            }
            
            @Test
            public void testGetAllUes() {
            	final List<com.mase2.mase2_project.model.Ue> ueList = ueDAO.getAllUes();
                assertEquals("Data fetch = data persisted", ueList.size(), 1);
				
            }
            
            
            
}
