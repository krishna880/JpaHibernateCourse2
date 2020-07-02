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
import com.kk.jpa.hibernate.entity.Student;
import com.kk.jpa.hibernate.repository.CourseRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JpaHibernateCourse2Application.class)
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
		TypedQuery<Course> typedQuery = em.createNamedQuery("query_get_all_courses",Course.class);
		List courseList =  typedQuery.getResultList();
		logger.info("Named query example - select c from course c -> {}",courseList);
	}
	
	@Test
	void findCoursesWithoutAnyStudents() {
		TypedQuery<Course> typedQuery = em.createQuery("select c from Course c where c.students is empty",Course.class);
		List courseList =  typedQuery.getResultList();
		logger.info("findCoursesWithoutAnyStudents -> {}",courseList);
	}
	
	@Test
	void findCoursesWithAtleastTwoStudents() {
		TypedQuery<Course> typedQuery = em.createQuery("select c from Course c where size(c.students) >= 2",Course.class);
		List courseList =  typedQuery.getResultList();
		logger.info("findCoursesWithAtleastTwoStudents -> {}",courseList);
	}
	
	@Test
	void findCoursesOrderedByStudents() {
		TypedQuery<Course> typedQuery = em.createQuery("select c from Course c order by size(c.students) desc",Course.class);
		List courseList =  typedQuery.getResultList();
		logger.info("findCoursesOrderedByStudents -> {}",courseList);
	}
	
	@Test
	void findStudentWithMatchingPassport() {
		TypedQuery<Student> typedQuery = em.createQuery("select s from Student s where s.studentpassport.number like '%1234%'",Student.class);
		List studentList =  typedQuery.getResultList();
		logger.info("findStudentWithMatchingPassport -> {}",studentList);
	}
	
	@Test
	void Innerjoin() {
		Query query = em.createQuery("Select c,cs from Course c JOIN c.students cs");
		List<Object[]> resultList = query.getResultList();
		logger.info("ResultSize -> {}",resultList.size());
		for(Object[] result : resultList) {
			logger.info("Course{} Student{}",result[0],result[1]);
		}
		
	}
	
	@Test
	void Leftjoin() {
		Query query = em.createQuery("Select c,cs from Course c LEFT JOIN c.students cs");
		List<Object[]> resultList = query.getResultList();
		logger.info("ResultSize -> {}",resultList.size());
		for(Object[] result : resultList) {
			logger.info("Course{} Student{}",result[0],result[1]);
		}
		
	}
	
	@Test
	void Crossjoin() {
		Query query = em.createQuery("Select c,s from Course c,Student s");
		List<Object[]> resultList = query.getResultList();
		logger.info("ResultSize -> {}",resultList.size());
		for(Object[] result : resultList) {
			logger.info("Course{} Student{}",result[0],result[1]);
		}
		
	}
	
	
	
}
