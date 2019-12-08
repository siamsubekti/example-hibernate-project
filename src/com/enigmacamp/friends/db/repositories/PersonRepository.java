package com.enigmacamp.friends.db.repositories;

import java.util.List;

//import javax.persistence.Table;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;

import org.hibernate.Query;
import org.hibernate.Transaction;


import com.enigmacamp.friends.db.entities.Person;


public class PersonRepository extends Repository<Person> {
	public PersonRepository() {
		this.entity = this.query.from(Person.class);
	}
	
	public List<Person> findAll(Predicate... predicates) {
		CriteriaQuery<Person> cq = this.query.select(this.entity);
		cq.where(predicates);
		
		return this.executeQuery(cq).getResultList();
	}
	
	public Person find(Integer id) {
		return (Person) session.get(Person.class, id);
	}
	
	
	public Person AddFriends (Person person) {
	
	Transaction trx = this.session.beginTransaction();
	
	session.save(person);
	session.getTransaction().commit();
	System.out.println("Insert done" + "\n");

	return person;
}

	public Person save(Person person) {

		Transaction trx = this.session.beginTransaction();

		Integer id = (Integer) this.session.save(person);
		person = this.find(id);
		trx.commit();

		return person;
	}
	
	public void delete(Person person) {
		
		try {
			Transaction trx = this.session.beginTransaction();
			
			this.session.delete(person);

			trx.commit();
			
			System.out.println("Removed  : "+ person.getName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("MASIH ADA RELASI TIDAK BISA DIHAPUS");
		}
	}
	
	
	public List<Person> getFriends(Person friend){
		// TODO list friends of a person;
		Person friends = this.session.get(Person.class, friend.getId());
		return friends.getPersonHasFriend();
//		return session.get(Person.class, getFriends(person));
		
	}
	
	public List<Person> mutual(Person person, Person friends) {
		List<Person> mutual = this.getFriends(person);
		
		mutual.retainAll(friends.getPersonHasFriend());
		return mutual;
		
	}


	
}
