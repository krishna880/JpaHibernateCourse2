package com.kk.jpa.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.kk.jpa.hibernate.entity.Course;
import com.kk.jpa.hibernate.entity.Review;
import com.kk.jpa.hibernate.entity.Student;
import com.kk.jpa.hibernate.repository.CourseRepository;
import com.kk.jpa.hibernate.repository.StudentRepository;

@SpringBootApplication
public class JpaHibernateCourse2Application implements CommandLineRunner {

	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(JpaHibernateCourse2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/*
		 * studentRepository.saveStudentWithPassport();
		 * studentRepository.saveStudentWithCourse(new Student("Thor"),new
		 * Course("Unix in 25 steps"));
		 * 
		 * List<Review> reviews = new ArrayList<Review>(); reviews.add(new Review("5",
		 * "Hats Off")); reviews.add(new Review("5", "Must watch"));
		 * courseRepository.addReviewsForCourse(10003L,reviews);
		 */
	}

}
