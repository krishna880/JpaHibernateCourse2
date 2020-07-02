package com.kk.jpa.hibernate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name="get_all_courses",query = "select c from Course c")
public class Course {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "fullname",nullable = false)
	private String name;

	public Course(String name) {
		super();
		this.name = name;
	}

	protected Course() {
		
	}
	
	public Long getId() {
		return id;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + "]";
	}
	
	

}
