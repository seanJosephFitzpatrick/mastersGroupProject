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

import com.mase2.mase2_project.data.FailureClassDAO;
import com.mase2.mase2_project.data.MccMncDAO;
import com.mase2.mase2_project.model.BaseData;
import com.mase2.mase2_project.model.EventCause;
import com.mase2.mase2_project.model.EventCausePK;
import com.mase2.mase2_project.model.FailureClass;
import com.mase2.mase2_project.model.MccMnc;
import com.mase2.mase2_project.model.MccMncPK;
import com.mase2.mase2_project.model.Ue;
import com.mase2.mase2_project.rest.JaxRsActivator;
import com.mase2.mase2_project.rest.MccMncWS;
import com.mase2.mase2_project.test.utils.UtilsDAO;




		@RunWith(Arquillian.class)
		public class MccMncTest {
			
			@Deployment
			public static Archive<?> createTestArchive() {
				return ShrinkWrap
						.create(JavaArchive.class, "Testing.jar")
						.addClasses(MccMncDAO.class, MccMnc.class,
								MccMncPK.class,
								JaxRsActivator.class,MccMncWS.class,
								UtilsDAO.class, FailureClassDAO.class, BaseData.class, EventCause.class, EventCausePK.class, FailureClass.class, Ue.class)
					//	.addPackage(EventCause.class.getPackage())
					//	.addPackage(EventCauseDAO.class.getPackage())
								//this line will pick up the production db
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
