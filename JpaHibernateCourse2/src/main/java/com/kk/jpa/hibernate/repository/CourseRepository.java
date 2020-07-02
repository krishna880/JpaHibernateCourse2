/**
 * 
 */
package com.kk.jpa.hibernate.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.kk.jpa.hibernate.entity.Course;
import com.kk.jpa.hibernate.entity.Review;

/**
 * @author krish
 *
 */
@Repository
@Transactional
public class CourseRepository {

	@Autowired
	EntityManager em;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public Course findById(Long id) {
		return em.find(Course.class, id);
	}

	public void deleteById(Long id) {
		Course course = findById(id);
		em.remove(course);
	}

	public Course saveCourse(Course course) {
		if (course.getId() == null) {
			// insert the row
			em.persist(course);
		} else {
			em.merge(course);
		}
		return course;
	}

	public void playWithentityManager() {

		Course course1 = new Course("Jenkins in 100 steps");
		em.persist(course1);

		Course course2 = new Course("Node JS in 100 steps");
		em.persist(course2);
		em.flush(); // Flush just commits everything in DB until this line
		// until above line in DB you'll see that course 1 and course 2 name will be
		// updated in DB

		em.detach(course2);

		course1.setName("Jenkins in 100 steps - Updated");

		// As we have detached course 2 above, hence course 2 updated name won't be
		course2.setName("Node JS in 100 steps - Updated");

		// As we have refreshed course1 i.e. course 1 object will be reverted to what it
		// is in Database, hence course 1 updated name also won't be updated in DB
		em.refresh(course1);
	}
	
	public void addReviewsForCourse() {
		Course course = findById(10003L);
		logger.info("course reviews --> {}",course.getReviews());
		
		Review review1 = new Review("5", "great explanation"); 
		Review review2 = new Review("5", "Hats Off");
		
		// setting relationship
		course.addReview(review1);
		review1.setCourse(course);
		
		course.addReview(review2);
		review2.setCourse(course);
		
		em.persist(review1);
		em.persist(review2);
	}
	
	public void addReviewsForCourse(Long courseId,List<Review> reviews) {
		Course course = findById(courseId);
		logger.info("course reviews --> {}",course.getReviews());
		
		for(Review review : reviews) {
			course.addReview(review);
			review.setCourse(course);
			em.persist(review);
		}
	}

}
