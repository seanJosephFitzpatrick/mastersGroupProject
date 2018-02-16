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
import com.mase2.mase2_project.data.EventCauseDAO;
import com.mase2.mase2_project.data.FailureClassDAO;
import com.mase2.mase2_project.model.BaseData;
import com.mase2.mase2_project.model.BaseDataPK;
import com.mase2.mase2_project.model.EventCause;
import com.mase2.mase2_project.model.EventCausePK;
import com.mase2.mase2_project.model.FailureClass;
import com.mase2.mase2_project.model.MccMnc;
import com.mase2.mase2_project.model.MccMncPK;
import com.mase2.mase2_project.model.Ue;
import com.mase2.mase2_project.rest.FailureClassWS;
import com.mase2.mase2_project.rest.JaxRsActivator;
import com.mase2.mase2_project.test.utils.UtilsDAO;

/**
 * @author A00248114
 *
 */
	
		@RunWith(Arquillian.class)
		public class FailureClassWSTest {
			
			@Deployment
			public static Archive<?> createTestArchive() {
				return ShrinkWrap
						.create(JavaArchive.class, "TestFailureClassWS.jar")
						.addClasses(FailureClassDAO.class, FailureClass.class,
								JaxRsActivator.class, FailureClassWS.class,
								UtilsDAO.class, EventCauseDAO.class, BaseData.class, MccMnc.class, MccMncPK.class, BaseDataPK.class, EventCause.class, EventCausePK.class, Ue.class)
					//	.addPackage(FailureClass.class.getPackage())
					//	.addPackage(FailureClassDAO.class.getPackage())
								//this line will pick up the production db
						.addAsManifestResource("META-INF/persistence.xml",
								"persistence.xml")
						.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");

			}

			 
			@EJB
			private FailureClassWS failureClassWS;
			
			@EJB
			private FailureClassDAO failureClassDAO;
			
			@EJB
			private UtilsDAO utilsDAO;
			 
			@Before
			public void setUp() {
				//this function means that we start with an empty table
				//And add one Failure Class and description
				//it should be possible to test with an in memory db for efficiency
				utilsDAO.deleteTableBaseData();
				utilsDAO.deleteTableFailureClass();
				FailureClass failureClass=new FailureClass();
				failureClass.setFailureClass(2);
				failureClass.setDescription("MT ACCESS");
				failureClassDAO.save(failureClass);
			}
			
			@Test
			public void testGetAllFailureClassWS() {
				Response response = failureClassWS.findAllFailureClasses();
				List<FailureClass> failureClassList = (List<FailureClass>) response.getEntity();
				assertEquals(HttpStatus.SC_OK, response.getStatus());				
				assertEquals("Data fetch = data persisted", failureClassList.size(), 1);
				FailureClass failureClass = failureClassList.get(0);
				assertEquals(2,failureClass.getFailureClass());
				assertEquals("MT ACCESS",failureClass.getDescription());
			
			}
					
}
