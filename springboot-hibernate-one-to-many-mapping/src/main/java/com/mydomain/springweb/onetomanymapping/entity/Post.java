package com.mydomain.springweb.onetomanymapping.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "posts")
public class Post {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="title")
	private String title;
	
	@Column(name="description")
	private String descitption;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="postComment_fid", referencedColumnName = "id")
	List<Comment> comments = new ArrayList<>();

	
	public Post() {
		super();
	
	}


	public Post(String title, String descitption) {
		super();
		this.title = title;
		this.descitption = descitption;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getDescitption() {
		return descitption;
	}



	public void setDescitption(String descitption) {
		this.descitption = descitption;
	}



	public List<Comment> getComments() {
		return comments;
	}



	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	
			
}
