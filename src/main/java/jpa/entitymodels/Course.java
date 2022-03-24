package jpa.entitymodels;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@Entity
@ToString
@Table(name = "courses")
public class Course {
    // Initialized Constructor
    public Course(Integer cId, String cName, String cInstructor) {
        this.cId = cId;
        this.cName = cName;
        this.cInstructor = cInstructor;
    }

    // Non-initialized Constructor
    public Course() {
    }

    // Primary Key
    @Id
    @Column(name = "id")
    private Integer cId;

    @Column(name = "name")
    private String cName;

    @Column(name = "instructor")
    private String cInstructor;

}
