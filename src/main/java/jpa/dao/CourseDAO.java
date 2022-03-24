package jpa.dao;

import jpa.entitymodels.Course;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public interface CourseDAO {
    String PERSISTENCE_UNIT_NAME = "sms-db";
    EntityManagerFactory emFactoryObj =
            Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    List<Course> getAllCourses();
}
