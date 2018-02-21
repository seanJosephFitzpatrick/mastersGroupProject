package com.mase2.mase2_project.util;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
@Stateless
@LocalBean
public class TableClearer {
	
    @PersistenceContext
    private EntityManager em;
    
	public void deleteAllTables(){
		deleteBaseDataTable();
		em.createQuery("DELETE FROM MccMnc").executeUpdate();
		em.createQuery("DELETE FROM EventCause").executeUpdate();
		em.createQuery("DELETE FROM FailureClass").executeUpdate();
		em.createQuery("DELETE FROM Ue").executeUpdate();

	}
	public void deleteBaseDataTable(){
		em.createQuery("DELETE FROM BaseData").executeUpdate();
		em.createNativeQuery("ALTER TABLE base_data AUTO_INCREMENT=1").executeUpdate();
	}

}
