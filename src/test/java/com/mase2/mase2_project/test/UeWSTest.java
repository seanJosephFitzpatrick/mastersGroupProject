package com.mase2.mase2_project.test;

import static org.junit.Assert.assertEquals;

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
import com.mase2.mase2_project.data.FailureClassDAO;
import com.mase2.mase2_project.data.UeDAO;
import com.mase2.mase2_project.model.BaseData;
import com.mase2.mase2_project.model.BaseDataPK;
import com.mase2.mase2_project.model.EventCause;
import com.mase2.mase2_project.model.EventCausePK;
import com.mase2.mase2_project.model.FailureClass;
import com.mase2.mase2_project.model.MccMnc;
import com.mase2.mase2_project.model.MccMncPK;
import com.mase2.mase2_project.model.Ue;
import com.mase2.mase2_project.rest.JaxRsActivator;
import com.mase2.mase2_project.rest.UeWS;
import com.mase2.mase2_project.test.utils.UtilsDAO;


    //    @FixMethodOrder(MethodSorters.NAME_ASCENDING)
        @RunWith(Arquillian.class)
        public class UeWSTest {
            
            @Deployment
            public static Archive<?> createTestArchive() {
                return ShrinkWrap
                        .create(JavaArchive.class, "TestUeWS.jar")
                        .addClasses(MccMnc.class,
                                MccMncPK.class,
                                JaxRsActivator.class,
                                UtilsDAO.class, FailureClassDAO.class, BaseData.class, BaseDataPK.class, EventCause.class, EventCausePK.class, FailureClass.class, Ue.class, UeWS.class, UeDAO.class)
                    //    .addPackage(EventCause.class.getPackage())
                    //    .addPackage(EventCauseDAO.class.getPackage())
                // this line will pick up the production db
                        .addAsManifestResource("META-INF/persistence.xml",
                                "persistence.xml")
                        .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");

            }

             
            @EJB
            private com.mase2.mase2_project.rest.UeWS ueWS;
            
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
                Ue ue=new Ue();
                ue.setTac(100100);
                ue.setMarketingName("G410");
                ue.setManufacturer("Mitsubishi");
                ue.setAccessCapability("GSM 1800, GSM 900");
                ueDAO.save(ue);
            }
            
            @Test
            public void testGetAllUes() {
                Response response = ueWS.findAllUes();
				List<Ue> uEList = (List<Ue>) response.getEntity();
				assertEquals(HttpStatus.SC_OK, response.getStatus());				
				assertEquals("Data fetch = data persisted", uEList.size(), 1);
				Ue uE = uEList.get(0);
				assertEquals(100100, uE.getTac());
				assertEquals("G410", uE.getMarketingName());
				assertEquals("Mitsubishi", uE.getManufacturer());
				assertEquals("GSM 1800, GSM 900", uE.getAccessCapability());

            }
            
            
            
}
