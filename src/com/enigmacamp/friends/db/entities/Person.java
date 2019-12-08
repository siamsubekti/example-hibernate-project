package com.enigmacamp.friends.db.entities;
	
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity()
@Table(name = "person")
public class Person {
	
	@Id()
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(nullable = false, length = 150)
	private String name;
	
	@Column(nullable = false)
	private Date birthdate;
	
	@OneToMany
	private List<Post> post;
	
	@JoinTable (name = "person_has_person", joinColumns = {
			@JoinColumn(name = "person_id", referencedColumnName = "id", nullable = false)}, 
			inverseJoinColumns = { 
					@JoinColumn(name = "friend_id1", referencedColumnName = "id", nullable = false)
			})
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private List<Person> personHasFriend;
	
	@ManyToOne()
	@JoinColumn(name = "gender", nullable = false)
	private Gender gender;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	
	
	public List<Post> getPost() {
		return post;
	}

	public void setPost(List<Post> post) {
		this.post = post;
	}

	public List<Person> getPersonHasFriend() {
		return personHasFriend;
	}

	public void setPersonHasFriend(List<Person> personHasFriend) {
		this.personHasFriend = personHasFriend;
	}

	@Override
	public String toString() {
		return "Person id=" + id + " " + name + ", birthdate=" + birthdate + ", gender=" + gender + "]" +"";
	}
}
