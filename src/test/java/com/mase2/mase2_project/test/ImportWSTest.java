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


    //    @FixMethodOrder(MethodSorters.NAME_ASCENDING)
        @RunWith(Arquillian.class)
        public class ImportWSTest {
            
            @Deployment
            public static Archive<?> createTestArchive() {
                return ShrinkWrap
                        .create(JavaArchive.class, "TestExcelReader.jar")
                        .addClasses(MccMnc.class,
                                MccMncPK.class,
                                JaxRsActivator.class,
                                UtilsDAO.class, FailureClassDAO.class,MccMncDAO.class, BaseData.class, ExcelDAO.class, 
                                EventCause.class, BaseDataWS.class, BaseDataDAO.class, FailureClassWS.class,
                                MccMncWS.class, EventCauseWS.class,
                                EventCauseDAO.class,DateParam.class, EventCausePK.class,ImportWS.class, FailureClass.class,TableClearer.class,FileLogger.class,InvalidEntity.class,Validator.class, Ue.class, UeWS.class, UeDAO.class)
                        .addPackages(true, jxl.Sheet.class.getPackage())
                        .addPackages(true, jxl.Workbook.class.getPackage())
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
                        
                        
                // this line will pick up the production db
                        .addAsManifestResource("META-INF/persistence.xml",
                                "persistence.xml")
                        .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");

            }

             
           
            @EJB
            private UeDAO ueDAO;
            @EJB
            private ImportWS importWS;
            @EJB
            private ExcelDAO excelDAO;
        	@EJB
        	private BaseDataDAO baseDataDao;
        	@EJB
        	private FailureClassDAO failureClassDAO;
        	@EJB
        	private EventCauseDAO eventCauseDAO;
        	@EJB
        	private MccMncDAO mcc_mncDAO;
        	
        	@Before
        	public void setup(){
        		importWS.importAllData();
        	}
             
            
            @Test
            public void testImportAllData() {
            	final List<Ue> ueList = ueDAO.getAllUes();
                assertEquals("Data fetch = data persisted", ueList.size(), 99);
                final List<BaseData> baseDataList = baseDataDao.getAllBaseData();
        		assertEquals("Data fetch = data persisted", baseDataList.size(), 800);
        		final List<FailureClass> failureClassList = failureClassDAO.getAllFailureClasses();
				assertEquals("Data fetch = data persisted", failureClassList.size(), 5);
				final List<EventCause> eventCauseList = eventCauseDAO.getAllEventCauses();
				assertEquals("Data fetch = data persisted", eventCauseList.size(), 80);
				final List<MccMnc> mccMncList = mcc_mncDAO.getAllMcc_Mncs();
				assertEquals("Data fetch = data persisted", mccMncList.size(), 41);
            }
            
        	@Test
        	public void testImportBaseData() {
        		importWS.importBaseData();
        		final List<BaseData> baseDataList = baseDataDao.getAllBaseData();
        		assertEquals("Data fetch = data persisted", baseDataList.size(), 800);

        	}
       
            
}
