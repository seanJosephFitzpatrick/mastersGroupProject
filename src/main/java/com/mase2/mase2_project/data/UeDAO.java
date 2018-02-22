package com.mase2.mase2_project.data;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.mase2.mase2_project.model.Ue;


@Stateless
@LocalBean
public class UeDAO {
	@PersistenceContext
    private EntityManager entityManager;
    
	public List<Ue> getAllUes() {
		final Query query=entityManager.createQuery("SELECT w FROM Ue w");
        return query.getResultList();
    }
	
//	public List<Ue> getByTac(int ue) {
//    	Query query=em.createQuery("SELECT w FROM Ue AS w "+
//    								"WHERE w.tac LIKE ?1");
//    	query.setParameter(1, ue);
//        return query.getResultList();
//    }
//	public List<Ue> getByMarketingName(String marketingName) {
//    	Query query=em.createQuery("SELECT w FROM Ue AS w "+
//    								"WHERE w.marketing_name LIKE ?1");
//    	query.setParameter(1, "%"+marketingName+"%");
//        return query.getResultList();
//    }
//	
//	public List<Ue> getByManufacturer(String manufacturer) {
//    	Query query=em.createQuery("SELECT w FROM Ue AS w "+
//    								"WHERE w.manufacturer LIKE ?1");
//    	query.setParameter(1, "%"+manufacturer+"%");
//        return query.getResultList();
//    }
//	
//	public List<Ue> getByAccessCapability(String accessCapability) {
//    	Query query=em.createQuery("SELECT w FROM Ue AS w "+
//    								"WHERE w.access_capability LIKE ?1");
//    	query.setParameter(1, "%"+accessCapability+"%");
//        return query.getResultList();
//    }
//	
//	public Ue getUe(int tac ) {
//        return em.find(Ue.class, tac);
//    }
	
	public void save(final Ue ue){
		entityManager.persist(ue);
	}
	
//	public void update(Ue ue) {
//		em.merge(ue);
//	}
//	
//	public void delete(int tac) {
//		em.remove(getUe(tac));
//	}
//	public void deleteTable(){
//		em.createQuery("DELETE FROM ue").executeUpdate();
//	}

}
