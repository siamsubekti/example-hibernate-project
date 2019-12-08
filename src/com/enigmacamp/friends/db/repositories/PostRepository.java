package com.enigmacamp.friends.db.repositories;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.enigmacamp.friends.db.entities.Person;
import com.enigmacamp.friends.db.entities.Post;

public class PostRepository extends Repository<Post> {
	public PostRepository() {
		this.entity = this.query.from(Post.class);
	}
	
	public List<Post> findAll(Predicate... predicates) {
		CriteriaQuery<Post> cq = this.query.select(this.entity);
		cq.where(predicates);
		
		return this.executeQuery(cq).getResultList();
	}
	
	public Post find (Integer id) {
		return (Post) session.get(Post.class, id);
	}
	
	public Post findTitle (String title) {
		
		Query query = session.createQuery("FROM Post WHERE title = :title");
		query.setParameter("title", title);
		
		List<Post> result = query.list();
		
		return result.get(0);
	}
	
	public List<Post> findByPostDate () {
		List<Post> post = new ArrayList<Post>();
		Query query = session.createQuery("FROM Post order by post_date desc");
				
		return query.list();
	}
	
	public List<Post> findByPostToday () {
		List<Post> post = new ArrayList<Post>();
		
		Query query = session.createQuery("FROM Post where day(post_date) = day(now())");
		
		return query.list();
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<Post> listPostName(String post, String name) {
		Criteria postx = session.createCriteria(Post.class);
				postx.createAlias("categorys", "pr", Criteria.INNER_JOIN, 
				Restrictions.eq("pr.name", post));
				postx.createAlias("person", "fr", Criteria.INNER_JOIN,
				Restrictions.eq("fr.name", name));
				return postx.list();
	}

	
	public Post create (Post post) {
		
		Transaction trx = this.session.beginTransaction();
		
		session.save(post);
		session.getTransaction().commit();
		System.out.println("Insert done" + "\n");
	
		return post;
	}
	
	public Post update (Post post) {
		
		Transaction trx = this.session.beginTransaction();
		
		session.merge(post);
		session.getTransaction().commit();
		System.out.println("update done" + "\n");
		
		return post;
				
	}
	
	public void delete (Post post) {
		Transaction trx = this.session.beginTransaction();
		this.session.delete(post);
		
		session.getTransaction().commit();
		System.out.println("delete done"+ "\n");
	
	}
	
}
