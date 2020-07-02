package com.kk.jpa.hibernate;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

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
class CourseRepositoryJPQLTests {

private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	EntityManager em;

	@Test
	void findCourseById_nonTypedQueryTest() {
		Query query = em.createQuery("Select c from Course c");
		List courseList =  query.getResultList();
		logger.info("Select c from Course c -> {}",courseList);
	}
	
	@Test
	void findCourseById_TypedQueryTest() {
		TypedQuery<Course> typedQuery = em.createQuery("Select c from Course c",Course.class);
		List courseList =  typedQuery.getResultList();
		logger.info("Select c from Course c -> {}",courseList);
	}
	
	@Test
	void findCourseById_TypedQueryWithWhereTest() {
		TypedQuery<Course> typedQuery = em.createQuery("Select c from Course c where name like '%50 steps'",Course.class);
		List courseList =  typedQuery.getResultList();
		logger.info("Select c from Course c where name like '%50 steps -> {}",courseList);
	}
	
	@Test
	void findCourseById_NamedQueryTest() {
		TypedQuery<Course> typedQuery = em.createNamedQuery("get_all_courses",Course.class);
		List courseList =  typedQuery.getResultList();
		logger.info("Named query example - select c from course c -> {}",courseList);
	}
	
	
	
}
