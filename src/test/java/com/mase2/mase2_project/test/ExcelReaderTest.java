package com.mase2.mase2_project.test;

import static org.junit.Assert.assertEquals;
import java.io.IOException;
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
import com.mase2.mase2_project.rest.ExcelReader;
import com.mase2.mase2_project.rest.FailureClassWS;
import com.mase2.mase2_project.rest.JaxRsActivator;
import com.mase2.mase2_project.rest.MccMncWS;
import com.mase2.mase2_project.rest.UeWS;
import com.mase2.mase2_project.test.utils.UtilsDAO;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;


    //    @FixMethodOrder(MethodSorters.NAME_ASCENDING)
        @RunWith(Arquillian.class)
        public class ExcelReaderTest {
            
            @Deployment
            public static Archive<?> createTestArchive() {
                return ShrinkWrap
                        .create(JavaArchive.class, "TestExcelReader.jar")
                        .addClasses(MccMnc.class,
                                MccMncPK.class,
                                JaxRsActivator.class,
                                UtilsDAO.class, FailureClassDAO.class,MccMncDAO.class, BaseData.class, ExcelReader.class, 
                                jxl.Sheet.class , jxl.Workbook.class, jxl.Cell.class, BaseDataPK.class, EventCause.class, 
                                jxl.CellFormat.class, jxl.biff.BaseCellFeatures.class, jxl.CellFeatures.class, jxl.HeaderFooter.Contents.class,
                                jxl.HeaderFooter.class,
                                EventCauseDAO.class, EventCausePK.class, FailureClass.class, Ue.class, UeWS.class, UeDAO.class)
                      .addPackage(jxl.Sheet.class.getPackage())
                    //    .addPackage(EventCauseDAO.class.getPackage())
                // this line will pick up the production db
                        .addAsManifestResource("META-INF/persistence.xml",
                                "persistence.xml")
                        .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");

            }

             
            @EJB
            private Sheet sheet;
            @EJB
            private UeWS ueWS;
            @EJB
            private UeDAO ueDAO;
            @EJB
            private UtilsDAO utilsDao;
            @EJB
            private ExcelReader excelReader;
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
        	private FailureClassWS failureClassWS;
        	@EJB
        	private MccMncWS mcc_mncWS;
        	@EJB
        	private EventCauseEndpoint eventCauseEndpoint;
             
            @Before
            public void setUp() throws IOException {

//            	utilsDao.deleteTableBaseData();
//            	utilsDao.deleteTableUe();
//            	utilsDao.deleteTableEventCause();
//            	utilsDao.deleteTable();
//            	utilsDao.deleteTableFailureClass();
//     
//                excelReader.importData();

            }
            
//            @Test
//            public void testGetAllUes() {
//                List<Ue> ueList = ueDAO.getAllUes();
//                assertEquals("Data fetch = data persisted", ueList.size(), 99);
//            }
//            
//        	@Test
//        	public void testGetAllBaseData() {
//        		List<BaseData> baseDataList = baseDataDao.getAllBaseData();
//        		assertEquals("Data fetch = data persisted", baseDataList.size(), 1);
//        	}
//        	
//			@Test
//			public void testGetAllFailureClass() {
//				List<FailureClass> failureClassList = failureClassDAO.getAllFailureClasses();
//				assertEquals("Data fetch = data persisted", failureClassList.size(), 1);
//			}
//			
//			@Test
//			public void testGetAllEventCauses() {
//				List<EventCause> eventCauseList = eventCauseDAO.getAllEventCauses();
//				assertEquals("Data fetch = data persisted", eventCauseList.size(), 1);
//			}
//			
//			@Test
//			public void testGetAllMccMncs() {
//				List<MccMnc> mccMncList = mcc_mncDAO.getAllMcc_Mncs();
//				assertEquals("Data fetch = data persisted", mccMncList.size(), 1);
//			}
            
}
