package com.kk.jpa.hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.kk.jpa.hibernate.entity.Course;
import com.kk.jpa.hibernate.repository.CourseRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JpaHibernateCourse1Application.class)
class CourseRepositoryNativeQueryTests {

private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	EntityManager em;

	@Test
	void findCourseById_nativeQueryTest() {
		Query query = em.createNativeQuery("SELECT * FROM COURSE", Course.class);
		List courseList =  query.getResultList();
		logger.info("Native query example. SELECT * FROM COURSE -> {}",courseList);
	}
	
	@Test
	void findCourseById_nativeQueryWithPositionalParams() {
		Query query = em.createNativeQuery("SELECT * FROM COURSE where id = ?", Course.class);
		query.setParameter(1, 10001L);
		List courseList =  query.getResultList();
		logger.info("Native query example with positional parameter. SELECT * FROM COURSE where id = ? -> {}",courseList);
	}
	
	@Test
	void findCourseById_nativeQueryWithNamedParams() {
		Query query = em.createNativeQuery("SELECT * FROM COURSE where id = :id", Course.class);
		query.setParameter("id", 10001L);
		List courseList =  query.getResultList();
		logger.info("Native query example with named parameter. SELECT * FROM COURSE where id = :id -> {}",courseList);
	}
	
	

	
	
}
