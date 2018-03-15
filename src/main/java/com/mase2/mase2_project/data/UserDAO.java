package com.mase2.mase2_project.data;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.mase2.mase2_project.model.User;

@Stateless
@LocalBean
public class UserDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {
		final Query query = entityManager.createNamedQuery("User.findAll");
		return query.getResultList();
	}

	public void save(final User user) {
		entityManager.persist(user);
	}

	@SuppressWarnings("unchecked")
	public List<User> findByEmail(String email) {
		final Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.email = :email");
		query.setParameter("email", email);
		return query.getResultList();
	}

	public User findById(int id) {
		return entityManager.find(User.class, id);
	}

	public void update(User user) {
		entityManager.merge(user);
	}
}
