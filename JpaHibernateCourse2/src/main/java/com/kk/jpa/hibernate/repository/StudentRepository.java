/**
 * 
 */
package com.kk.jpa.hibernate.repository;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.kk.jpa.hibernate.entity.Course;
import com.kk.jpa.hibernate.entity.Passport;
import com.kk.jpa.hibernate.entity.Student;

/**
 * @author krish
 *
 */
@Repository
@Transactional
public class StudentRepository {

	@Autowired
	EntityManager em;

	public Student findById(Long id) {
		return em.find(Student.class, id);
	}

	public void deleteById(Long id) {
		Student student = findById(id);
		em.remove(student);
	}

	public Student saveStudent(Student student) {
		if (student.getId() == null) {
			// insert the row
			em.persist(student);
		} else {
			em.merge(student);
		}
		return student;
	}
	
	public void saveStudentWithPassport() {
		Passport passport = new Passport("L3900605");
		em.persist(passport);
		Student student = new Student("KK1");
		student.setStudentpassport(passport);
		em.persist(student);
	}
	
	public void saveStudentWithCourse(Student student,Course course) {
		//Student student = new Student("Thor");
		//Course course = new Course("Unix in 25 steps");
		em.persist(student);
		em.persist(course);
		
		student.addCourse(course);
		course.addStudent(student);
		em.persist(student);
		
		//query to check the results from H2 console
		//SELECT * FROM STUDENT_COURSE_ASSOCIATION,STUDENT,COURSE
		//WHERE
		//STUDENT_COURSE_ASSOCIATION.STUDENT_ID  = STUDENT.ID AND
		//STUDENT_COURSE_ASSOCIATION.COURSE_ID  = COURSE.ID 
	}

	public void playWithentityManager() {

		Student student1 = new Student("Jenkins in 100 steps");
		em.persist(student1);

		Student student2 = new Student("Node JS in 100 steps");
		em.persist(student2);
		em.flush(); // Flush just commits everything in DB until this line
		// until above line in DB you'll see that student 1 and student 2 name will be
		// updated in DB

		em.detach(student2);

		student1.setName("Jenkins in 100 steps - Updated");

		// As we have detached student 2 above, hence student 2 updated name won't be
		student2.setName("Node JS in 100 steps - Updated");

		// As we have refreshed student1 i.e. student 1 object will be reverted to what it
		// is in Database, hence student 1 updated name also won't be updated in DB
		em.refresh(student1);
	}

}
