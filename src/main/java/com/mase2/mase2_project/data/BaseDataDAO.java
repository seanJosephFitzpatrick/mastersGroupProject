package com.mase2.mase2_project.data;


import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;







import com.mase2.mase2_project.model.BaseData;
import com.mase2.mase2_project.util.DateParam;
import com.mase2.mase2_project.util.DurationAndCountObject;
import com.mase2.mase2_project.util.FailureCountObject;
import com.mase2.mase2_project.util.IMSIObject;
import com.mase2.mase2_project.util.TopTenFailuresObject;
import com.mase2.mase2_project.util.UniqueEventAndCauseObject;



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

	public List<FailureCountObject> getCountForCellIdAndDate(String model,DateParam startDateParam, DateParam endDateParam) {
		

		final Query query=entityManager.createQuery("SELECT new com.mase2.mase2_project.util.FailureCountObject(count(m)) FROM BaseData m where m.ue.model like ?1 and m.dateTime between ?2 and ?3")
				.setParameter(1, model)
				.setParameter(2, startDateParam.getDate())
				.setParameter(3, endDateParam.getDate());
        return query.getResultList();
	}
	
	public List<FailureCountObject> getCountForIMSIAndDate(String imsi,DateParam startDateParam, DateParam endDateParam) {
		

		final Query query=entityManager.createQuery("SELECT new com.mase2.mase2_project.util.FailureCountObject(count(m) as Failures) FROM BaseData m where m.imsi like ?1 and m.dateTime between ?2 and ?3")
				.setParameter(1, imsi)
				.setParameter(2, startDateParam.getDate())
				.setParameter(3, endDateParam.getDate());
        return query.getResultList();
	}

	public List<DurationAndCountObject> getSumDurationAndCountForEachIMSI(DateParam startDateParam, DateParam endDateParam) {

		final Query query=entityManager.createQuery("SELECT new com.mase2.mase2_project.util.DurationAndCountObject(m.imsi,count(m),sum(duration)) FROM BaseData m where m.dateTime between ?1 and ?2 group by m.imsi")
				.setParameter(1, startDateParam.getDate())
				.setParameter(2, endDateParam.getDate());
        return query.getResultList();
	}
	
	public List<TopTenFailuresObject> getTopTenFailures(DateParam startDateParam, DateParam endDateParam) {

		final Query query=entityManager.createQuery("SELECT new com.mase2.mase2_project.util.TopTenFailuresObject(m.mccMnc.id.mcc, m.mccMnc.id.mnc, m.cellId,count(m) as countfailures) FROM BaseData m where m.dateTime between ?1 and ?2 group by m.mccMnc.id.mcc, m.mccMnc.id.mnc, m.cellId order by countfailures desc limit 10")
				.setParameter(1, startDateParam.getDate())
				.setParameter(2, endDateParam.getDate());
        return query.setMaxResults(10).getResultList();
	}

	public List<IMSIObject> getAllImsiWithFailures(DateParam startDateParam, DateParam endDateParam) {
		
		final Query query=entityManager.createQuery("SELECT distinct new com.mase2.mase2_project.util.IMSIObject(m.imsi) FROM BaseData m where m.dateTime between ?1 and ?2")
				.setParameter(1, startDateParam.getDate())
				.setParameter(2, endDateParam.getDate());
        return query.getResultList();
	}

	public List<UniqueEventAndCauseObject> getUniqueEventIdAndCauseCodeForModel(String model) {
		final Query query=entityManager.createQuery("SELECT new com.mase2.mase2_project.util.UniqueEventAndCauseObject(m.eventCause,count(m)) FROM BaseData m where m.ue.model like ?1 group by m.eventCause")
				.setParameter(1, model);
				
        return query.getResultList();
	}

}
