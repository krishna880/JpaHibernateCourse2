package com.kk.jpa.hibernate;


import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.kk.jpa.hibernate.entity.Passport;
import com.kk.jpa.hibernate.entity.Student;
import com.kk.jpa.hibernate.repository.StudentRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JpaHibernateCourse2Application.class)
public class StudentRepositoryTests {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private EntityManager em;

	@Test
	@Transactional
	public void retreiveStudentAndPassportDetails() {
		Student student = em.find(Student.class, 20001L);
		logger.info("Student --> {}", student);
		// If we remove @Transactional annotation then below line - student.getPassport() will fail because there is no persitence context available.
		//@Transactional is responsible for the persistence context to be
		// started at the start of the method and ends at end of method
		logger.info("Passport --> {}", student.getStudentpassport());
	}
	
	@Test
	@Transactional
	public void retreivePassportWithStudentDetails() {
		Passport passport = em.find(Passport.class, 40001L);
		logger.info("Passport --> {}", passport);
		// If we remove @Transactional annotation then below line - passport.getStudent() will fail because there is no persitence context available.
				//@Transactional is responsible for the persistence context to be
				// started at the start of the method and ends at end of method
		logger.info("Student --> {}", passport.getStudent());
	}
	
	@Test
	@Transactional
	public void retreiveStudentWithCoursesDetails() {
		Student student = em.find(Student.class, 20001L);
		logger.info("Student --> {}", student);
		logger.info("Courses --> {}", student.getCourses());
	}

}
