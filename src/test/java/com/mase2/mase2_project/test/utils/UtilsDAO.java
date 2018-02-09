package com.mase2.mase2_project.test.utils;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
      
}
