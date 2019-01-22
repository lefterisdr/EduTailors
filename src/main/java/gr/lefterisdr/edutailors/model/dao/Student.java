package gr.lefterisdr.edutailors.model.dao;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Student implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "student_id")
    private int id;
    private String firstname;
    private String lastname;
    @Column(length=10)
    @Enumerated(EnumType.STRING)
    private SexType sex;
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private Instant birthdate;
//    @OneToMany(mappedBy = "studentCourseId.student", cascade=CascadeType.ALL)
//    private Set<StudentCourse> studentCourses = new HashSet<>();

    public enum SexType
    {
        Male, Female
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getFirstname()
    {
        return firstname;
    }

    public void setFirstname(String firstname)
    {
        this.firstname = firstname;
    }

    public String getLastname()
    {
        return lastname;
    }

    public void setLastname(String lastname)
    {
        this.lastname = lastname;
    }

    public SexType getSex()
    {
        return sex;
    }

    public void setSex(SexType sex)
    {
        this.sex = sex;
    }

    public Instant getBirthdate()
    {
        return birthdate;
    }

    public void setBirthdate(Instant birthdate)
    {
        this.birthdate = birthdate;
    }

//    public Set<StudentCourse> getStudentCourses()
//    {
//        return studentCourses;
//    }
//
//    public void setStudentCourses(Set<StudentCourse> studentCourses)
//    {
//        this.studentCourses = studentCourses;
//    }
}
