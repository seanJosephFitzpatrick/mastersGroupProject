package com.mase2.mase2_project.data;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.mase2.mase2_project.model.FailureClass;

@Stateless
@LocalBean
public class FailureClassDAO {
	
	@PersistenceContext
    private EntityManager entityManager;
    
	public List<FailureClass> getAllFailureClasses() {
		final Query query=entityManager.createQuery("SELECT w FROM FailureClass w");
        return query.getResultList();
    }
	
	public void save(final FailureClass failureClass){
		entityManager.persist(failureClass);
	}
	
	/*
	public List<FailureClass> getByFailureClass(int failureClass) {
    	Query query=em.createQuery("SELECT w FROM FailureClass AS w "+
    								"WHERE w.failureClass LIKE ?1");
    	query.setParameter(1, failureClass);
        return query.getResultList();
    }
	public List<FailureClass> getByDescription(String description) {
    	Query query=em.createQuery("SELECT w FROM FailureClass AS w "+
    								"WHERE w.description LIKE ?1");
    	query.setParameter(1, "%"+description+"%");
        return query.getResultList();
    }
	
	public FailureClass getFailureClass(int failureClass ) {
        return em.find(FailureClass.class, failureClass);
    }
	
	public void update(FailureClass failureClass) {
		em.merge(failureClass);
	}
	
	public void delete(int failureClass) {
		em.remove(getFailureClass(failureClass));
	}
	public void deleteTable(){
		em.createQuery("DELETE FROM FailureClass").executeUpdate();
	}
	*/

}
