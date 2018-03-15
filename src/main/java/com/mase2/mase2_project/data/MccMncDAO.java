package com.mase2.mase2_project.data;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.mase2.mase2_project.model.MccMnc;

@Stateless
@LocalBean
public class MccMncDAO {
	@PersistenceContext
    private EntityManager entityManager;
    
	@SuppressWarnings("unchecked")
	public List<MccMnc> getAllMcc_Mncs() {
		final Query query=entityManager.createQuery("SELECT m FROM MccMnc m");
        return query.getResultList();
    }
	
	public void save(final MccMnc mccMnc){
		entityManager.persist(mccMnc);
	}
}
