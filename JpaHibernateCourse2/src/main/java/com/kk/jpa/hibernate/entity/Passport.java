package com.kk.jpa.hibernate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Passport {

	@Id
	@GeneratedValue
	private Long idp;

	@Column(nullable = false)
	private String number;
	
	//This is for creating bi-directional one-to-one mapping. If we don't mention mappedBy property then 
	//hibernate will create a column in Passport table as well by the name student_id. The mappedBy should be mentioned 
	//in the non-receiving end i.e. the entity where the join column should not be created
	@OneToOne(fetch=FetchType.LAZY,mappedBy = "studentpassport")
	private Student student;

	protected Passport() {
	}

	public Passport(String number) {
		this.number = number;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Long getId() {
		return idp;
	}
	

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	@Override
	public String toString() {
		return String.format("Passport[%s]", number);
	}
}