package com.kk.jpa.hibernate;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import com.kk.jpa.hibernate.entity.Course;
import com.kk.jpa.hibernate.repository.CourseSpringDataRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JpaHibernateCourse2Application.class)
class CourseSpringDataRepositoryTests {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CourseSpringDataRepository courseSpringDataRepository;
	
	@Test
	public void findById_CoursePresent() {
		Optional<Course> courseOptional = courseSpringDataRepository.findById(10001L);
		logger.info("courseOptional.isPresent() --> {}",courseOptional.isPresent());
	}
	
	@Test
	public void playingAroundWithSpringDataRepo() {
		//below will insert the course
		Course course = new Course("Python in 100 steps");
		courseSpringDataRepository.save(course);
		
		//below will update the course
		course.setName("Python in 50 steps");
		courseSpringDataRepository.save(course);
		
		logger.info("find all --> {}",courseSpringDataRepository.findAll());
		logger.info("count --> {}",courseSpringDataRepository.count());
		
		
		logger.info("sorted find all --> {}",courseSpringDataRepository.findAll(Sort.by(Sort.Direction.DESC, "name")));
		
	}
	
	@Test
	public void pagination() {
		
		Pageable pageRequest = PageRequest.of(0, 3);
		Page<Course> firstPage = courseSpringDataRepository.findAll(pageRequest);
		logger.info("courses in first page-->",firstPage.getContent());
		
		Pageable nextPageRequest = firstPage.nextPageable();
		Page<Course> nextPage = courseSpringDataRepository.findAll(nextPageRequest);
		logger.info("courses in second page-->",nextPage.getContent());
	}
}
