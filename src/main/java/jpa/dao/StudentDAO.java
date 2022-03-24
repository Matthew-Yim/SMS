package jpa.dao;

import jpa.entitymodels.Course;
import jpa.entitymodels.Student;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public interface StudentDAO {
    String PERSISTENCE_UNIT_NAME = "sms-db";
    EntityManagerFactory emFactoryObj =
            Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    List<Student> getAllStudents();

    Student getStudentByEmail(String sEmail);

    boolean validateStudent(String sEmail, String sPassword);

    void registerStudentToCourse(String sEmail, int cId);

    List<Course> getStudentCourses(String sEmail);
}
