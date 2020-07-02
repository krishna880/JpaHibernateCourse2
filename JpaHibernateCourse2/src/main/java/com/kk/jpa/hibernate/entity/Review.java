package com.kk.jpa.hibernate.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Review implements java.io.Serializable{

	@Id
	@GeneratedValue
	private Long id;

	private String rating;

	private String description;
	
	@ManyToOne
	private Course course;
	
	

	protected Review() {
	}

	public Review(String rating, String description) {
		this.rating = rating;
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public Long getId() {
		return id;
	}
	

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	

	@Override
	public String toString() {
		return String.format("Review[%s %s]", rating, description);
	}

}