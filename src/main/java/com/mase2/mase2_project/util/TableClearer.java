package com.mase2.mase2_project.util;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
@Stateless
@LocalBean
public class TableClearer {
	
    @PersistenceContext
    private EntityManager entityManager;
    
	public void deleteAllTables(){
		deleteBaseDataTable();
		entityManager.createQuery("DELETE FROM MccMnc").executeUpdate();
		entityManager.createQuery("DELETE FROM EventCause").executeUpdate();
		entityManager.createQuery("DELETE FROM FailureClass").executeUpdate();
		entityManager.createQuery("DELETE FROM Ue").executeUpdate();

	}
	public void deleteBaseDataTable(){
		entityManager.createQuery("DELETE FROM BaseData").executeUpdate();
		entityManager.createNativeQuery("ALTER TABLE base_data AUTO_INCREMENT=1").executeUpdate();
	}
	public void deleteCallFailuresTables(){
		entityManager.createQuery("DELETE FROM FailureClass").executeUpdate();
	}

}
