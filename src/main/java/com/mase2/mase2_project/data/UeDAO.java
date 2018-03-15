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
    
	@SuppressWarnings("unchecked")
	public List<Ue> getAllUes() {
		final Query query=entityManager.createQuery("SELECT w FROM Ue w");
        return query.getResultList();
    }
	
	public void save(final Ue ue){
		entityManager.persist(ue);
	}

}
