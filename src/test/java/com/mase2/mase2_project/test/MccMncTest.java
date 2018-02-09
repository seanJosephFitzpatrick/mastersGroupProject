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

import com.mase2.mase2_project.data.FailureClassDAO;
import com.mase2.mase2_project.data.MccMncDAO;
import com.mase2.mase2_project.model.BaseData;
import com.mase2.mase2_project.model.BaseDataPK;
import com.mase2.mase2_project.model.EventCause;
import com.mase2.mase2_project.model.EventCausePK;
import com.mase2.mase2_project.model.FailureClass;
import com.mase2.mase2_project.model.MccMnc;
import com.mase2.mase2_project.model.MccMncPK;
import com.mase2.mase2_project.model.Ue;
import com.mase2.mase2_project.rest.JaxRsActivator;
import com.mase2.mase2_project.rest.MccMncWS;
import com.mase2.mase2_project.test.utils.UtilsDAO;




	//	@FixMethodOrder(MethodSorters.NAME_ASCENDING)
		@RunWith(Arquillian.class)
		public class MccMncTest {
			
			@Deployment
			public static Archive<?> createTestArchive() {
				return ShrinkWrap
						.create(JavaArchive.class, "Testing.jar")
						.addClasses(MccMncDAO.class, MccMnc.class,
								MccMncPK.class,
								JaxRsActivator.class,MccMncWS.class,
								UtilsDAO.class, FailureClassDAO.class, BaseData.class, BaseDataPK.class, EventCause.class, EventCausePK.class, FailureClass.class, Ue.class)
					//	.addPackage(EventCause.class.getPackage())
					//	.addPackage(EventCauseDAO.class.getPackage())
								//this line will pick up the production db
						.addAsManifestResource("META-INF/persistence.xml",
								"persistence.xml")
						.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");

			}

			 
			@EJB
			private MccMncWS mcc_mncWS;
			
			@EJB
			private MccMncDAO mcc_mncDAO;
			
			@EJB
			private UtilsDAO utilsDAO;
			 
			@Before
			public void setUp() {
				utilsDAO.deleteTable();
				MccMncPK mccMncPK = new MccMncPK();
				mccMncPK.setMcc(238);
				mccMncPK.setMnc(1);
				MccMnc mccMnc=new MccMnc();
				mccMnc.setId(mccMncPK);
				mccMnc.setCountry("Sweden");
				mccMnc.setOperator("TDC-DK");
				mcc_mncDAO.save(mccMnc);
			}
			
			@Test
			public void testGetAllMccMncs() {
				List<MccMnc> mccMncList = mcc_mncDAO.getAllMcc_Mncs();
				assertEquals("Data fetch = data persisted", mccMncList.size(), 1);
			}
			
			
			
}
