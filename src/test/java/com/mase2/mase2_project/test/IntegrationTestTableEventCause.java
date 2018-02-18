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
import com.mase2.mase2_project.data.EventCauseDAO;
import com.mase2.mase2_project.data.FailureClassDAO;
import com.mase2.mase2_project.model.BaseData;
import com.mase2.mase2_project.model.EventCause;
import com.mase2.mase2_project.model.EventCausePK;
import com.mase2.mase2_project.model.FailureClass;
import com.mase2.mase2_project.model.MccMnc;
import com.mase2.mase2_project.model.MccMncPK;
import com.mase2.mase2_project.model.Ue;
import com.mase2.mase2_project.rest.EventCauseEndpoint;
import com.mase2.mase2_project.rest.JaxRsActivator;
import com.mase2.mase2_project.test.utils.UtilsDAO;

/**
 * @author A00248114
 *
 */

	//	@FixMethodOrder(MethodSorters.NAME_ASCENDING)
		@RunWith(Arquillian.class)
		public class IntegrationTestTableEventCause {
			
			@Deployment
			public static Archive<?> createTestArchive() {
				return ShrinkWrap
						.create(JavaArchive.class, "TestEventCause.jar")
						.addClasses(EventCauseDAO.class, EventCause.class,
								EventCausePK.class,
								JaxRsActivator.class,EventCauseEndpoint.class,
								UtilsDAO.class, FailureClassDAO.class, BaseData.class, MccMnc.class, MccMncPK.class, EventCause.class, EventCausePK.class, FailureClass.class, Ue.class)
					//	.addPackage(EventCause.class.getPackage())
					//	.addPackage(EventCauseDAO.class.getPackage())
								//this line will pick up the production db
						.addAsManifestResource("META-INF/persistence.xml",
								"persistence.xml")
						.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");

			}

			 
			@EJB
			private EventCauseEndpoint eventCauseEndpoint;
			
			@EJB
			private EventCauseDAO eventCauseDAO;
			
			@EJB
			private UtilsDAO utilsDAO;
			 
			@Before
			public void setUp() {
				//this function means that we start with an empty table
				//And add one wine
				//it should be possible to test with an in memory db for efficiency
				utilsDAO.deleteTableBaseData();
				utilsDAO.deleteTableEventCause();
				EventCausePK eventCausePK = new EventCausePK();
				eventCausePK.setEventId("4097");
				eventCausePK.setEventCode("3");
				EventCause eventCause=new EventCause();
				eventCause.setId(eventCausePK);
				eventCause.setDescription("RRC CONN SETUP-EUTRAN GENERATED REASON");
				eventCauseDAO.save(eventCause);
			}
			
			@Test
			public void testGetAllEventCauses() {
				List<EventCause> eventCauseList = eventCauseDAO.getAllEventCauses();
				assertEquals("Data fetch = data persisted", eventCauseList.size(), 1);
			}
			
			
			
}
