package com.mase2.mase2_project.model;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;



@Stateless
@LocalBean
public class Mcc_MncDAO {
	@PersistenceContext
    private EntityManager em;
    
	public List<MccMnc> getAllMcc_Mncs() {
    	Query query=em.createQuery("SELECT w FROM MccMnc w");
        return query.getResultList();
    }
	
//	public List<MccMnc> getByFailureClass(int failureClass) {
//    	Query query=em.createQuery("SELECT w FROM MccMnc AS w "+
//    								"WHERE w.failureClass LIKE ?1");
//    	query.setParameter(1, failureClass);
//        return query.getResultList();
//    }
//	public List<MccMnc> getByDescription(String description) {
//    	Query query=em.createQuery("SELECT w FROM MccMnc AS w "+
//    								"WHERE w.description LIKE ?1");
//    	query.setParameter(1, "%"+description+"%");
//        return query.getResultList();
//    }
//	
//	public MccMnc getFailureClass(int failureClass ) {
//        return em.find(MccMnc.class, failureClass);
//    }
//	
//	public void save(MccMnc failureClass){
//		em.persist(failureClass);
//	}
//	
//	public void update(MccMnc failureClass) {
//		em.merge(failureClass);
//	}
//	
//	public void delete(int failureClass) {
//		em.remove(getFailureClass(failureClass));
//	}
//	public void deleteTable(){
//		em.createQuery("DELETE FROM MccMnc").executeUpdate();
//	}

}
