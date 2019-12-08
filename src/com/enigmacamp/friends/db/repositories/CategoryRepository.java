package com.enigmacamp.friends.db.repositories;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;

import com.enigmacamp.friends.db.entities.Category;

public class CategoryRepository extends Repository<Category> {
	public CategoryRepository () {
		this.entity = this.query.from(Category.class);
	}
	
	public List<Category> findAll (Predicate... predicates) {
		CriteriaQuery<Category> cq = this.query.select(this.entity);
		cq.where(predicates);
		
		return this.executeQuery(cq).getResultList();
	}
	
	public Category find (Integer id) {
		
		return (Category) session.get(Category.class, id);
	}

}
