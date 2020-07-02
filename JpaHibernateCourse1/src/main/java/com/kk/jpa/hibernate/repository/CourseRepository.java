/**
 * 
 */
package com.kk.jpa.hibernate.repository;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.kk.jpa.hibernate.entity.Course;

/**
 * @author krish
 *
 */
@Repository
@Transactional
public class CourseRepository {

	@Autowired
	EntityManager em;

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

}
