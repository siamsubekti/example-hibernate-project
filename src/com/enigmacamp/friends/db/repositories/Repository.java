package com.enigmacamp.friends.db.repositories;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.enigmacamp.hbm.config.HibernateConfig;

@SuppressWarnings("unchecked")
public class Repository<T> {
    protected SessionFactory sessionFactory = HibernateConfig.getSessionFactory();
    protected Session session = sessionFactory.openSession();
    protected CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
	protected CriteriaQuery<T> query = (CriteriaQuery<T>) criteriaBuilder.createQuery();
	protected Root<T> entity;
	
	public Root<T> getEntity() {
		return entity;
	}
	
	public CriteriaBuilder getCriteriaBuilder() {
		return criteriaBuilder;
	}
    
    protected TypedQuery<T> executeQuery(CriteriaQuery<T> query) {
    	return session.createQuery(query);
    }
    
    protected Transaction getTransaction() {
    	return this.session.beginTransaction();
    }
}
