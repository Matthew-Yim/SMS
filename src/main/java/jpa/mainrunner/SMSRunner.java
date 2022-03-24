package jpa.mainrunner;

import jpa.entitymodels.Course;
import jpa.entitymodels.Student;
import jpa.service.CourseService;
import jpa.service.StudentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SMSRunner {
    // System Variables
    private static Scanner input = new Scanner(System.in);
    private static StudentService studentService = new StudentService();
    private static CourseService courseService = new CourseService();
    private static String emailResponse;
    private static String passwordResponse;
    private static Student student;
    private Course course;

    public static void main(String[] args) {
       welcomeStartUp();
       initalResponse();
       emailResponse();
       passwordResponse();
       welcomeUser();

    }// Main
    public static void welcomeStartUp(){
        System.out.println("Hello, Welcome to the Student Management System");
        System.out.print("Are you currently a(n)\n1. Student\n2. Quit\n");
    } // Greets the user and asks for response

    public static void initalResponse(){
        String response;
        boolean doCheckPoint;
        do {
            doCheckPoint = true;
            System.out.println("Please select either 1 or 2.");
            response = input.nextLine();
            if (!response.equals("1")) {
                if (!response.equals("2")) {
                    System.out.println("Response Invalid");
                    doCheckPoint = false;
                }
                else{
                    quitProgram();
                }
            }
        }while (!doCheckPoint);
    } // Asks the user for a selection of either 1. Student or 2. Quit

    public static void emailResponse(){
        boolean doCheckPoint;
        do {
            doCheckPoint = true;
            System.out.println("Please Enter Your Email");
            emailResponse = input.nextLine();
            if (!emailResponse.contains("@")) {
                System.out.println("Response Invalid");
                doCheckPoint = false;
            }
            else {
                student = studentService.getStudentByEmail(emailResponse);
                if (student == null) {
                    System.out.println("Sorry this Email is not Associated with any Students, try again!");
                    doCheckPoint = false;
                }
            }
        }while (!doCheckPoint);
    }

    public static void passwordResponse(){
        boolean doCheckPoint;
        do {
            doCheckPoint = true;
            System.out.println("Please Enter Your Password");
            passwordResponse = input.nextLine();
            student = studentService.getStudentByEmail(emailResponse);
            if (!student.getSPass().equals(passwordResponse)){
                System.out.println("Sorry this password is not associated the given email, try again!");
                doCheckPoint = false;
            }
        }while (!doCheckPoint);
    }

    public static void welcomeUser(){
        String userName = studentService.getStudentByEmail(emailResponse).getSName();
        userName = userName.replaceAll("\n", "");
        System.out.printf("Welcome %s to the Student Management System.\n", userName);
        tableViewUserCourse();
        System.out.println();
        int outcome = userResponse();
        if (outcome == 1){
            tableViewAllCourse();
            addCourse();
            tableViewUserCourse();
        }
    }

    public static int userResponse(){
        String response;
        boolean doCheckPoint;
        do {
            doCheckPoint = true;
            System.out.println("Please Select from the following:\n(1) Register to Class\n(2) Logout");
            response = input.nextLine();
            if (!response.equals("1")) {
                if (!response.equals("2")) {
                    System.out.println("Response Invalid");
                    doCheckPoint = false;
                }
                else{
                    logoutQuitProgram();
                }
            }
        }while (!doCheckPoint);
        return 1;
    }
    public static void tableViewUserCourse(){
        int numCourses = studentService.getStudentCourses(emailResponse).size();
        System.out.printf("My class:\n%10s %10s %20s %20s","#", "Course Id", "Course Name", "Instructor Name\n");
        for (int i = 0; i < numCourses; i++) {
            Course courseLocalObject = studentService.getStudentCourses(emailResponse).get(i);
            String instructorName = courseLocalObject.getCInstructor();
            instructorName = instructorName.replaceAll("\n", "");
            System.out.printf("%10d %10d %40s %20s\n",
                    i,
                    courseLocalObject.getCId(),
                    courseLocalObject.getCName(),
                    instructorName);
        }
    }

    public static void tableViewAllCourse(){
        System.out.printf("All Courses:\n%s %20s %20s", "Course Id", "Course Name", "Instructor Name\n");
        courseService.getAllCourses().forEach(courses -> {
            String instructorName = courses.getCInstructor();
            instructorName = instructorName.replaceAll("\n","");
            System.out.printf("%10d %40s %20s\n",
                    courses.getCId(),
                    courses.getCName(),
                    instructorName);
        });
    }

    public static void addCourse(){
        boolean doCheckPoint;
        int cIdSelection;
        do {
            doCheckPoint = true;
            List<Integer> listCourseId = new ArrayList<>();
            System.out.println("Please make a selection for the available courses to register");
            cIdSelection = input.nextInt();
            courseService.getAllCourses().forEach(course-> listCourseId.add(course.getCId()));
            if (!listCourseId.contains(cIdSelection)){
                System.out.printf("Selection invalid, there are no available courses with course ID: %d\n", cIdSelection);
                doCheckPoint = false;
            }
        }while (!doCheckPoint); // Do While End
        studentService.registerStudentToCourse(emailResponse, cIdSelection);
    }

    public static void logoutQuitProgram(){
        System.out.println("You have been signed out\nStudent Management System will now terminate\nGoodbye!");
        System.exit(0);
    }

    public static void quitProgram(){
        System.out.println("Student Management System will now terminate\nGoodbye!");
        System.exit(0);
    }
}// Class
