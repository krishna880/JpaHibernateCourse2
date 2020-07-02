package com.kk.jpa.hibernate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.kk.jpa.hibernate.entity.Course;
import com.kk.jpa.hibernate.repository.CourseRepository;

@SpringBootApplication
public class JpaHibernateCourse1Application implements CommandLineRunner {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CourseRepository courseRepository;

	public static void main(String[] args) {
		SpringApplication.run(JpaHibernateCourse1Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/*
		 * Course course = courseRepository.findById(10001L);
		 * logger.info("Course with Id 10001 is --> {}",course);
		 */
		
		courseRepository.playWithentityManager();
		
	}

}
