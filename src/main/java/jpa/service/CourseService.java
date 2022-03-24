package jpa.service;

import jpa.dao.CourseDAO;
import jpa.entitymodels.Course;
import jpa.entitymodels.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class CourseService implements CourseDAO {
    // This method takes no parameter and returns every Course in the table.
    @Override
    public List<Course> getAllCourses() {
        // Connection / Create Query as String / Load Query into "TypedQuery"[type] queue[name]
       try {
           EntityManager em = emFactoryObj.createEntityManager();
           String sql = "SELECT c FROM Course c";
           TypedQuery<Course> query = em.createQuery(sql, Course.class);
           return query.getResultList();
       }
       catch (Exception e) {
           System.out.println("Error occurred with CourseService, Most likely with Hibernate...");
           e.printStackTrace();
           return null;
       }
    }
}
