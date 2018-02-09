package com.mase2.mase2_project.test.utils;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
@LocalBean
public class UtilsDAO {

    @PersistenceContext
    private EntityManager em;
    
	public void deleteTable(){
		em.createQuery("DELETE FROM MccMnc").executeUpdate();
		em.createNativeQuery("ALTER TABLE mcc_mnc AUTO_INCREMENT=1")
		.executeUpdate();
		
	}
	
	public void deleteTableEventCause(){
		em.createQuery("DELETE FROM EventCause").executeUpdate();
		em.createNativeQuery("ALTER TABLE event_cause AUTO_INCREMENT=1")
		.executeUpdate();
		
	}
	public void deleteTableBaseData(){
		em.createQuery("DELETE FROM BaseData").executeUpdate();
		em.createNativeQuery("ALTER TABLE base_data AUTO_INCREMENT=1")
		.executeUpdate();
		
	}
      
}
