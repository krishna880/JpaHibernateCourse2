package com.kk.jpa.hibernate;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.kk.jpa.hibernate.entity.Course;
import com.kk.jpa.hibernate.repository.CourseRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JpaHibernateCourse1Application.class)
class CoureRepositoryTests {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CourseRepository courseRepository;

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

}
