package com.mase2.mase2_project.data;

import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.joda.time.DateTime;

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

	public List<BaseData> getCountForCellIdAndDate(String cellId,DateParam dateParam) {
		

		final Query query=entityManager.createQuery("SELECT count(m) FROM BaseData m where m.cellId like ?1 and m.dateTime between ?2 and ?3")
				.setParameter(1, cellId)
				.setParameter(2, dateParam.getDate())
				.setParameter(3, new Date());
        return query.getResultList();
	}

	public List<BaseData> getSumDurationAndCountForEachIMSI(DateParam dateParam) {
		DateTime dt = new DateTime();

		final Query query=entityManager.createQuery("SELECT m.imsi,count(m),sum(duration) FROM BaseData m where m.dateTime between ?1 and ?2 group by m.imsi")
				.setParameter(1, dateParam.getDate())
				.setParameter(2, new Date());
        return query.getResultList();
	}

	public List<BaseData> getAllImsiWithFailures(DateParam dateParam) {
		DateTime dt = new DateTime();

		final Query query=entityManager.createQuery("SELECT distinct m.imsi FROM BaseData m where m.dateTime between ?1 and ?2")
				.setParameter(1, dateParam.getDate())
				.setParameter(2, new Date());
        return query.getResultList();
	}

	public List<BaseData> getUniqueEventIdAndCauseCodeForModel(String cellId) {
		final Query query=entityManager.createQuery("SELECT m.eventCause,count(m) FROM BaseData m where m.cellId like ?1 group by m.eventCause")
				.setParameter(1, cellId);
				
        return query.getResultList();
	}

}
