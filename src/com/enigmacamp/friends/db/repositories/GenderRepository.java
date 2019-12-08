package com.enigmacamp.friends.db.repositories;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;

import com.enigmacamp.friends.db.entities.Gender;

public class GenderRepository extends Repository<Gender> {
	public GenderRepository() {
		this.entity = this.query.from(Gender.class);
	}
	
	public List<Gender> findAll(Predicate... predicates) {
		CriteriaQuery<Gender> cq = this.query.select(this.entity);
		cq.where(predicates);
		
		return this.executeQuery(cq).getResultList();
	}
	
	public Gender find(Integer id) {
		return (Gender) session.get(Gender.class, id);
	}
}
