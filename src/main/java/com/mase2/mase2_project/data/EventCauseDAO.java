package com.mase2.mase2_project.data;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import com.mase2.mase2_project.model.EventCause;

/**
 * @author A00248114
 *
 */

@Stateless
@LocalBean
public class EventCauseDAO {
	
	@PersistenceContext
    private EntityManager em;
    
	public List<EventCause> getAllEventCauses() {
    	Query query=em.createQuery("SELECT w FROM EventCause w");
        return query.getResultList();
    }
	/*
	public EventCause getById(int id) {
        return em.find(EventCause.class, id);
    }
	
	public List<EventCause> getByDescription(String description) {
    	Query query=em.createQuery("SELECT w FROM EventCause AS w "+
    								"WHERE w.description LIKE ?1");
    	query.setParameter(1, "%"+description+"%");
        return query.getResultList();
    }
	
	public EventCause getEventCause(int id ) {
        return em.find(EventCause.class, id);
    }
	*/
	public void save(EventCause eventCause){
		em.persist(eventCause);
	}
	/*
	public void update(EventCause eventCause) {
		em.merge(eventCause);
	}
	
	public void delete(int id) {
		em.remove(getEventCause(id));
	}
	public void deleteTable(){
		em.createQuery("DELETE FROM event_cause").executeUpdate();
	}
	*/
}
