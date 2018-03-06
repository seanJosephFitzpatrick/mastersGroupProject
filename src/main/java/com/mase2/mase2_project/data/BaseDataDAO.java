package com.mase2.mase2_project.data;


import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


import com.mase2.mase2_project.model.BaseData;
import com.mase2.mase2_project.util.DateParam;



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

	public List<BaseData> getBaseDataForIMSI(String imsi) {
		final Query query=entityManager.createQuery("SELECT m.eventCause FROM BaseData m where m.imsi like ?1")
				.setParameter(1, imsi);
        return query.getResultList();
	}

	public List<BaseData> getCountForCellIdAndDate(String model,DateParam startDateParam, DateParam endDateParam) {
		

		final Query query=entityManager.createQuery("SELECT count(m) FROM BaseData m where m.ue.model like ?1 and m.dateTime between ?2 and ?3")
				.setParameter(1, model)
				.setParameter(2, startDateParam.getDate())
				.setParameter(3, endDateParam.getDate());
        return query.getResultList();
	}

	public List<BaseData> getSumDurationAndCountForEachIMSI(DateParam startDateParam, DateParam endDateParam) {

		final Query query=entityManager.createQuery("SELECT m.imsi,count(m) as numFailures,sum(duration) as sumDuration FROM BaseData m where m.dateTime between ?1 and ?2 group by m.imsi")
				.setParameter(1, startDateParam.getDate())
				.setParameter(2, endDateParam.getDate());
        return query.getResultList();
	}
	
	public List<BaseData> getTopTenFailures(DateParam startDateParam, DateParam endDateParam) {

		final Query query=entityManager.createQuery("SELECT m.mccMnc.id.mcc, m.mccMnc.id.mnc, m.cellId,count(m) as countfailures FROM BaseData m where m.dateTime between ?1 and ?2 group by m.mccMnc.id.mcc, m.mccMnc.id.mnc, m.cellId order by countfailures desc limit 10")
				.setParameter(1, startDateParam.getDate())
				.setParameter(2, endDateParam.getDate());
        return query.setMaxResults(10).getResultList();
	}

	public List<BaseData> getAllImsiWithFailures(DateParam startDateParam, DateParam endDateParam) {
		
		final Query query=entityManager.createQuery("SELECT distinct m.imsi FROM BaseData m where m.dateTime between ?1 and ?2")
				.setParameter(1, startDateParam.getDate())
				.setParameter(2, endDateParam.getDate());
        return query.getResultList();
	}
	
	/*
	public List<BaseData> getUniqueEventIdAndCauseCodeForModel(String cellId) {
		final Query query=entityManager.createQuery("SELECT m.eventCause,count(m) FROM BaseData m where m.cellId like ?1 group by m.eventCause")
				.setParameter(1, cellId);
				
        return query.getResultList();
	}
	*/
	
	public List<BaseData> getUniqueEventIdAndCauseCodeForModel(String model) {
		final Query query=entityManager.createQuery("SELECT m.eventCause,count(m) FROM BaseData m where m.ue.model like ?1 group by m.eventCause")
				.setParameter(1, model);
				
        return query.getResultList();
	}

}
