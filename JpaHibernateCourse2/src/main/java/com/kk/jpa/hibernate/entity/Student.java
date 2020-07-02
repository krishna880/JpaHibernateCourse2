package com.kk.jpa.hibernate.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

@Entity
public class Student {

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String name;
	
	//H2 database will automatically create a new field in student table with the name studentpassport_idp because idp is the primary key in passport table
	@OneToOne(fetch = FetchType.LAZY)
	//If you want a custom name for this column then mention the column name in below Joincolumn
	//@JoinColumn(name = "idofpassport")
	private Passport studentpassport;
	
	//one student can have many courses and one course can hav many students. This relationship is many-to-many
	//joinColumns is for the owning side table columns
	//inverseJoinColumns is for the non-owning side table columns
	@ManyToMany
	@JoinTable(name="student_course_association", 
	joinColumns = @JoinColumn(name="student_id"),
	inverseJoinColumns = @JoinColumn(name="course_id"))
	private List<Course> courses = new ArrayList<>();

	protected Student() {
	}

	public Student(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}
	

	public Passport getStudentpassport() {
		return studentpassport;
	}

	public void setStudentpassport(Passport studentpassport) {
		this.studentpassport = studentpassport;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void addCourse(Course course) {
		this.courses.add(course);
	}
	
	public void removeCourse(Course course) {
		this.courses.remove(course);
	}
	
	

	@Override
	public String toString() {
		return String.format("Student[%s]", name);
	}
}