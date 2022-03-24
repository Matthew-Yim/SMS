package jpa.entitymodels;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@ToString
@Table(name = "students")
public class Student {
    // Initialized Constructor
    public Student(String sEmail, String sName, String sPass, List<Course> sCourses) {
        this.sEmail = sEmail;
        this.sName = sName;
        this.sPass = sPass;
        this.sCourses = sCourses;
    }

    // Non-initialized Constructor
    public Student() {
    }

    // Primary Key
    @Id
    @Column(name = "email")
    private String sEmail;

    @Column(name = "name")
    private String sName;

    @Column(name = "password")
    private String sPass;

    // Relationships
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "student_courses",
            joinColumns = {
                    @JoinColumn(name = "student_email")},
            inverseJoinColumns = {
                    @JoinColumn(name = "course_id")})
    private List<Course> sCourses = new ArrayList<>();

}// Class