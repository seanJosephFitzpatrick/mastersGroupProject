package com.mase2.mase2_project.test.utils;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
@LocalBean
public class UeUtilsDAO {

	 @PersistenceContext
	    private EntityManager em;
	    
		public void deleteTable(){
			em.createQuery("DELETE FROM Ue").executeUpdate();
			
		}
}
