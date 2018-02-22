package com.mase2.mase2_project.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
import com.mase2.mase2_project.model.EventCause;
import com.mase2.mase2_project.model.EventCausePK;
import com.mase2.mase2_project.model.FailureClass;
import com.mase2.mase2_project.model.MccMnc;
import com.mase2.mase2_project.model.MccMncPK;
import com.mase2.mase2_project.model.Ue;
import com.mase2.mase2_project.rest.EventCauseWS;
import com.mase2.mase2_project.rest.JaxRsActivator;
import com.mase2.mase2_project.test.utils.UtilsDAO;

/**
 * @author A00248114
 *
 */

	//	@FixMethodOrder(MethodSorters.NAME_ASCENDING)
		@RunWith(Arquillian.class)
		public class EventCauseWSTest {
			
			@Deployment
			public static Archive<?> createTestArchive() {
				return ShrinkWrap
						.create(JavaArchive.class, "TestEventCauseWS.jar")
						.addClasses(EventCauseDAO.class, EventCause.class,
								EventCausePK.class,
								JaxRsActivator.class,EventCauseWS.class,
								UtilsDAO.class, FailureClassDAO.class, BaseData.class, MccMnc.class, MccMncPK.class, EventCause.class, EventCausePK.class, FailureClass.class, Ue.class)
					//	.addPackage(EventCause.class.getPackage())
					//	.addPackage(EventCauseDAO.class.getPackage())
								//this line will pick up the production db
						.addAsManifestResource("META-INF/persistence.xml",
								"persistence.xml")
						.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");

			}

			 
			@EJB
			private EventCauseWS eventCauseEndpoint;
			@EJB
			private EventCauseDAO eventCauseDAO;
			@EJB
			private UtilsDAO utilsDAO;
			
			private EventCause eventCause;
			 
			@Before
			public void setUp() {
				//this function means that we start with an empty table
				//And add one wine
				//it should be possible to test with an in memory db for efficiency
				utilsDAO.deleteTableBaseData();
				utilsDAO.deleteTableEventCause();
				final EventCausePK eventCausePK = new EventCausePK();
				eventCausePK.setEventId("4097");
				eventCausePK.setEventCode("3");
				eventCause=new EventCause();
				eventCause.setId(eventCausePK);
				eventCause.setDescription("S1 SIG CONN SETUP-S1 INTERFACE DOWN");
				eventCauseDAO.save(eventCause);
			}
			
			@Test
			public void testGetAllEventCauses() {
				final Response response = eventCauseEndpoint.listAll();
				List<EventCause> eventCauseList = (List<EventCause>) response.getEntity();
				assertEquals(HttpStatus.SC_OK, response.getStatus());				
				assertEquals("Data fetch = data persisted", eventCauseList.size(), 1);
				final EventCause eventCause = eventCauseList.get(0);
				assertEquals("4097", eventCause.getId().getEventId());
				assertEquals("3", eventCause.getId().getEventCode());
				assertEquals("S1 SIG CONN SETUP-S1 INTERFACE DOWN", eventCause.getDescription());
				
			}
			@Test
			public void testMccMncPKEqual(){
				final EventCausePK eventCausePK = new EventCausePK();
				eventCausePK.setEventId("4097");
				eventCausePK.setEventCode("3");
				assertTrue(eventCausePK.equals(eventCause.getId()));
				assertTrue(eventCausePK.equals(eventCausePK));
				
			}
			@Test
			public void testMccMncPKUnequal(){
				final EventCausePK eventCausePK = new EventCausePK();
				eventCausePK.setEventId("4037");
				eventCausePK.setEventCode("3");
				assertFalse(eventCausePK.equals(eventCause.getId()));
				final String test="";
				assertFalse(eventCausePK.equals(test));
				
			}
			
			
			
}
