package com.mase2.mase2_project.data;


import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;





import com.mase2.mase2_project.graph_model.LastNode;
import com.mase2.mase2_project.graph_model.NodeDataTime;
import com.mase2.mase2_project.graph_model.NodeEventIdCouseCode;
import com.mase2.mase2_project.model.BaseData;
import com.mase2.mase2_project.model.EventCause;
import com.mase2.mase2_project.util.AutoComObject;
import com.mase2.mase2_project.util.DateParam;
import com.mase2.mase2_project.util.DurationAndCountObject;
import com.mase2.mase2_project.util.FailureCountObject;
import com.mase2.mase2_project.util.IMSIObject;
import com.mase2.mase2_project.util.TopTenFailuresObject;
import com.mase2.mase2_project.util.TopTenIMSIsObject;
import com.mase2.mase2_project.util.UniqueEventAndCauseObject;



@Stateless
@LocalBean
public class BaseDataDAO {
	@PersistenceContext
    private EntityManager entityManager;
    
	@EJB
	EventCauseDAO eventCauseDAO;
	
	@SuppressWarnings("unchecked")
	public List<BaseData> getAllBaseData() {
		final Query query=entityManager.createQuery("SELECT m FROM BaseData m");
        return query.getResultList();
    }
	
	public void save(final BaseData baseData){
		entityManager.persist(baseData);
	}

	@SuppressWarnings("unchecked")
	public List<BaseData> getBaseDataForIMSI(String imsi) {
		final Query query=entityManager.createQuery("SELECT m.eventCause FROM BaseData m where m.imsi like ?1")
				.setParameter(1, imsi);
        return query.getResultList();
	}
	@SuppressWarnings("unchecked")
	public List<IMSIObject> getUniqueCauseCodesForIMSI(String imsi) {
		final Query query=entityManager.createQuery("SELECT distinct new com.mase2.mase2_project.util.IMSIObject(m.eventCause.id.eventCode) FROM BaseData m where m.imsi like ?1")
				.setParameter(1, imsi);
        return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<FailureCountObject> getCountForCellIdAndDate(String model,DateParam startDateParam, DateParam endDateParam) {
		

		final Query query=entityManager.createQuery("SELECT new com.mase2.mase2_project.util.FailureCountObject(count(m)) FROM BaseData m where m.ue.model like ?1 and m.dateTime between ?2 and ?3")
				.setParameter(1, model)
				.setParameter(2, startDateParam.getDate())
				.setParameter(3, endDateParam.getDate());
        return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<FailureCountObject> getCountForIMSIAndDate(String imsi,DateParam startDateParam, DateParam endDateParam) {
		

		final Query query=entityManager.createQuery("SELECT new com.mase2.mase2_project.util.FailureCountObject(count(m) as Failures) FROM BaseData m where m.imsi like ?1 and m.dateTime between ?2 and ?3")
				.setParameter(1, imsi)
				.setParameter(2, startDateParam.getDate())
				.setParameter(3, endDateParam.getDate());
        return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<DurationAndCountObject> getSumDurationAndCountForEachIMSI(DateParam startDateParam, DateParam endDateParam) {

		final Query query=entityManager.createQuery("SELECT new com.mase2.mase2_project.util.DurationAndCountObject(m.imsi,count(m),sum(duration)) FROM BaseData m where m.dateTime between ?1 and ?2 group by m.imsi")
				.setParameter(1, startDateParam.getDate())
				.setParameter(2, endDateParam.getDate());
        return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<TopTenFailuresObject> getTopTenFailures(DateParam startDateParam, DateParam endDateParam) {

		final Query query=entityManager.createQuery("SELECT new com.mase2.mase2_project.util.TopTenFailuresObject(m.mccMnc.country, m.mccMnc.operator, m.cellId,count(m) as countfailures) FROM BaseData m where m.dateTime between ?1 and ?2 group by m.mccMnc.country, m.mccMnc.operator, m.cellId order by countfailures desc limit 10")
				.setParameter(1, startDateParam.getDate())
				.setParameter(2, endDateParam.getDate());
        return query.setMaxResults(10).getResultList();
	}
	@SuppressWarnings("unchecked")
	public List<Integer> getTotalNumberOfFailures() {

		final Query query=entityManager.createQuery("SELECT count(*) FROM BaseData m");

        return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<IMSIObject> getAllImsiWithFailures(DateParam startDateParam, DateParam endDateParam) {
		
		final Query query=entityManager.createQuery("SELECT distinct new com.mase2.mase2_project.util.IMSIObject(m.imsi) FROM BaseData m where m.dateTime between ?1 and ?2")
				.setParameter(1, startDateParam.getDate())
				.setParameter(2, endDateParam.getDate());
		
        return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getAllImsi(String imsi) {
		
		final Query query=entityManager.createQuery("SELECT distinct m.imsi FROM BaseData m WHERE m.imsi LIKE :imsi ORDER BY m.imsi DESC")
		.setParameter("imsi", '%' +imsi+'%' );
		return query.setMaxResults(5).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getAllModels(String model) {
		
		final Query query=entityManager.createQuery("SELECT distinct m.ue.model FROM BaseData m WHERE m.ue.model LIKE :model")
		.setParameter("model", '%' +model+'%' );
		return query.setMaxResults(5).getResultList();
	}
	public List<AutoComObject> getAllFailureClasses(String failureClass) {
		final Query query=entityManager.createQuery("SELECT distinct new com.mase2.mase2_project.util.AutoComObject(m.description,m.failureClass) FROM FailureClass m WHERE m.description LIKE :failureClass")
				.setParameter("failureClass", '%' +failureClass+'%' );
				return query.setMaxResults(5).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<UniqueEventAndCauseObject> getUniqueEventIdAndCauseCodeForModel(String model) {
		final Query query=entityManager.createQuery("SELECT new com.mase2.mase2_project.util.UniqueEventAndCauseObject(m.eventCause,count(m)) FROM BaseData m where m.ue.model like ?1 group by m.eventCause")
				.setParameter(1, model);
				
        return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<TopTenIMSIsObject> getTopTenIMSIs(DateParam startDateParam, DateParam endDateParam) {
		final Query query=entityManager.createQuery("SELECT new com.mase2.mase2_project.util.TopTenIMSIsObject(m.imsi,count(m) as countfailures) FROM BaseData m where m.dateTime between ?1 and ?2 group by m.imsi order by countfailures desc")
				.setParameter(1, startDateParam.getDate())
				.setParameter(2, endDateParam.getDate());
        return query.setMaxResults(10).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<IMSIObject> getIMSIsForGivenFaiureCauseClass(String failure) {
		final Query query=entityManager.createQuery("SELECT new com.mase2.mase2_project.util.IMSIObject(m.imsi) FROM BaseData m where m.failureClassBean.failureClass like :failure ORDER BY m.imsi DESC")
				.setParameter("failure", '%' +failure+'%' );
		return query.getResultList();
	}

	public List<NodeEventIdCouseCode> getBaseDataForIMSIGraph(String imsi) {
		List<NodeEventIdCouseCode> result = new ArrayList<>();
		Query query = entityManager.createQuery("SELECT m FROM BaseData m where m.imsi like ?1 ").setParameter(1, imsi);
		List<BaseData> baseDatas = query.getResultList();
		List<EventCause> eventCauses = eventCauseDAO.getAllEventCauses();
		NodeEventIdCouseCode nodeEventIdCouseCode = new NodeEventIdCouseCode();
		List<BaseData> basedataForTheSameEvents = new ArrayList<>();
		NodeDataTime nodeDataTime = new NodeDataTime();
		List<NodeDataTime> nodeEventIdCouseCodeChildren = new ArrayList<>();;
		

		List<NodeDataTime>nodeDataTimeChildren = new ArrayList<>();

		
		List<BaseData> eventIdCouseCodeChildList = new ArrayList<>();
		
		for (EventCause eventCause : eventCauses) {
			query = entityManager.createQuery("Select m FROM BaseData m where m.imsi like ?1 and m.eventCause.id.eventCode like ?2  and m.eventCause.id.eventId like ?3 ");
			query.setParameter(1, imsi);
			query.setParameter(2, eventCause.getId().getEventCode());
			query.setParameter(3, eventCause.getId().getEventId());
			
			List<BaseData> baseDataForImsiAndEvenCause = query.getResultList();
			System.out.println("baseDataForImsiAndEvenCaouse " +baseDataForImsiAndEvenCause.size());
			nodeEventIdCouseCode = new NodeEventIdCouseCode();
			for (BaseData baseData : baseDataForImsiAndEvenCause) {
				nodeDataTime = new NodeDataTime(baseData.getDateTime().toString());
				nodeDataTime.addChild(new LastNode(baseData.getImsi()));
				nodeDataTime.addChild(new LastNode(baseData.getDateTime().toString()));
				nodeDataTime.addChild(new LastNode(baseData.getDuration()+""));
				nodeDataTime.addChild(new LastNode(baseData.getFailureClassBean().getDescription()));
				nodeEventIdCouseCode.addChildren(nodeDataTime);
				nodeEventIdCouseCode.setName(eventCause.getDescription());
				
			}
			if(nodeEventIdCouseCode.getChildren().size() != 0) {
				result.add(nodeEventIdCouseCode);
			}
			
			

		}
		System.out.println("BaseDataDAO.getBaseDataForIMSIGraph()result.size "  + result.size());
		
		return result;
		
	}



	

}
