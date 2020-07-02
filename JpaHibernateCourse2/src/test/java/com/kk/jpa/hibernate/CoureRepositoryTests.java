package com.kk.jpa.hibernate;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.kk.jpa.hibernate.entity.Course;
import com.kk.jpa.hibernate.entity.Review;
import com.kk.jpa.hibernate.repository.CourseRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JpaHibernateCourse2Application.class)
class CoureRepositoryTests {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private EntityManager em;

	@Test
	void findCourseByIdTest() {
		Course course =  courseRepository.findById(10001L);
		assertEquals("JPA in 50 steps", course.getName());
	}
	
	@Test
	@DirtiesContext
	void deleteCourseByIdTest() {
		courseRepository.deleteById(10002L);
		assertNull(courseRepository.findById(10002L));
	}
	
	@Test
	@DirtiesContext
	void saveCourseTest() {
		Course course = courseRepository.findById(10001L);
		assertEquals("JPA in 50 steps", course.getName());
		course.setName("JPA in 50 steps - Updated");
		courseRepository.saveCourse(course);
		
		Course course1 = courseRepository.findById(10001L);
		assertEquals("JPA in 50 steps - Updated", course1.getName());
	}
	
	@Test
	@Transactional
	void retreiveReviewsForCourse() {
		Course course = courseRepository.findById(10001L);
		// If we remove @Transactional annotation then below line - course.getReviews() will fail because there is no persitence context available.
		//@Transactional is responsible for the persistence context to be
		// started at the start of the method and ends at end of method
		logger.info("reviews for course --> {}",course.getReviews());
	}
	
	@Test
	void retreiveCourseForReview() {
		Review review = em.find(Review.class, 50001L);
		// If we remove @Transactional annotation then below line - review.getCourse() will fail because there is no persitence context available.
		//@Transactional is responsible for the persistence context to be
		// started at the start of the method and ends at end of method
		logger.info("course for review --> {}",review.getCourse());
	}

}
