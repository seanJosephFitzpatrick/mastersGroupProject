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

	@SuppressWarnings("unchecked")
	public List<FailureClass> getAllFailureClasses() {
		final Query query = entityManager.createQuery("SELECT w FROM FailureClass w");
		return query.getResultList();
	}

	public void save(final FailureClass failureClass) {
		entityManager.persist(failureClass);
	}

}
