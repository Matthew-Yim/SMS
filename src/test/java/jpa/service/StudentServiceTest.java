package jpa.service;

import jpa.dao.StudentDAO;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StudentServiceTest {


    @BeforeEach
    void setUp() {
        // Setting preliminary stuff needed to run test
        // Which is none at the moment
    }

    @Test
    public void validateStudent_should_return_true_given_correct_email_and_password() {
        String email = "aiannitti7@is.gd";
        String password = "TWP4hf5j";

        StudentDAO studentService = new StudentService();
        boolean actualResult = studentService.validateStudent(email, password);

        Assertions.assertTrue(actualResult);
    }

    @Test
    public void validateStudent_should_return_false_given_correct_email_and_incorrect_password() {
        String email = "aiannitti7@is.gd";
        String password = "coconutInvalid";

        StudentDAO studentService = new StudentService();
        boolean actualResult = studentService.validateStudent(email, password);

        Assertions.assertFalse(actualResult);
    }

    @Test
    public void validateStudent_should_fail() {
        String email = "aiannitti7@is.gd";
        String password = "coconutInvalid";

        StudentDAO studentService = new StudentService();
        boolean actualResult = studentService.validateStudent(email, password);

        Assertions.assertTrue(actualResult);
    }
}

