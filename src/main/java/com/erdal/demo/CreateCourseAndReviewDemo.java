package com.erdal.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.erdal.entity.Course;
import com.erdal.entity.Instructor;
import com.erdal.entity.InstructorDetail;
import com.erdal.entity.Review;

public class CreateCourseAndReviewDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure().addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class).buildSessionFactory();

		try (factory; Session session = factory.getCurrentSession()) {
			session.beginTransaction();
			Course c = new Course("Intro to Programming");
			c.addReview(new Review("What a course, whew!"));

			System.out.println("Saving course");
			System.out.println(c);
			System.out.println(c.getReviews());

			session.persist(c);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}