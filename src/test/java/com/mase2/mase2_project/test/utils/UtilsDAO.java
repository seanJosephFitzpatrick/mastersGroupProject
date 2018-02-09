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
	
	public void deleteTableFailureClass(){
		em.createQuery("DELETE FROM FailureClass").executeUpdate();
		em.createNativeQuery("ALTER TABLE failure_class AUTO_INCREMENT=1")
		.executeUpdate();
		
	}
      
}
