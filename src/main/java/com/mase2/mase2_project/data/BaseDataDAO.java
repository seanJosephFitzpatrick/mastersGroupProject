package com.mase2.mase2_project.data;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.mase2.mase2_project.model.BaseData;



@Stateless
@LocalBean
public class BaseDataDAO {
	@PersistenceContext
    private EntityManager entityManager;
    
	public List<BaseData> getAllBaseData() {
		final Query query=entityManager.createQuery("SELECT m FROM BaseData m");
        return query.getResultList();
    }
	
	public void save(final BaseData baseData){
		entityManager.persist(baseData);
	}

}
