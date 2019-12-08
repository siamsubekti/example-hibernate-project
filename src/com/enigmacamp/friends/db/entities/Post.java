package com.enigmacamp.friends.db.entities;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "post")
public class Post {
	
	@Id()
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column()
	private String title;
	
	@Column()
	private String content;
	
	@Column(nullable = false)
	private Date post_date;
	
	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "person", nullable = false)
	private Person person;
	
	@ManyToMany (cascade = {
			CascadeType.PERSIST,
			CascadeType.MERGE
	})
	@JoinTable (name = "post_has_category",
			joinColumns = @JoinColumn(name = "post_id"),
			inverseJoinColumns = @JoinColumn(name = "category_id"))
	private List<Category> categorys = new ArrayList<>();
	
	public Post () {
		this.post_date = new Date(System.currentTimeMillis());
	}
	
	public Post (Integer id, String title, String content, Person person) throws ParseException {
		this.id = id;
		this.title = title;
		this.content = content;
		this.post_date = new Date(System.currentTimeMillis());
		this.person = person;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getPost_date() {
		return post_date;
	}
	public void setPost_date(Date post_date) {
		this.post_date = post_date;
	}
	public Person getPerson_id() {
		return person;
	}
	public void setPerson_id(Person person) {
		this.person = person;
	}
	
	public List<Category> getCategorys() {
		return categorys;
	}

	public void setCategorys(List<Category> categorys) {
		this.categorys = categorys;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", title=" + title + ", content=" + content + ", post_date=" + post_date + ", person="
				+ person + "]"+", Category="+ categorys.stream().map(Category::getName).collect(Collectors.toList()) + "\n";
	}


	
}
