package com.mase2.mase2_project.test.utils;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
@LocalBean
public class UtilsDAO {

    @PersistenceContext
    private EntityManager entityManager;
    
	public void deleteTable(){
		entityManager.createQuery("DELETE FROM MccMnc").executeUpdate();
		entityManager.createNativeQuery("ALTER TABLE mcc_mnc AUTO_INCREMENT=1")
		.executeUpdate();
	}
	
	public void deleteTableEventCause(){
		entityManager.createQuery("DELETE FROM EventCause").executeUpdate();
		entityManager.createNativeQuery("ALTER TABLE event_cause AUTO_INCREMENT=1")
		.executeUpdate();		
	}

	public void deleteTableBaseData(){
		entityManager.createQuery("DELETE FROM BaseData").executeUpdate();
		entityManager.createNativeQuery("ALTER TABLE base_data AUTO_INCREMENT=1").executeUpdate();
	}

	public void deleteTableFailureClass(){
		entityManager.createQuery("DELETE FROM FailureClass").executeUpdate();
		entityManager.createNativeQuery("ALTER TABLE failure_class AUTO_INCREMENT=1").executeUpdate();	
	}
	
	public void deleteTableUe(){
		entityManager.createQuery("DELETE FROM Ue").executeUpdate();		
	}
}
