package gr.lefterisdr.edutailors.model.dao;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@Embeddable
public class StudentCourseId implements Serializable
{
    @ManyToOne(cascade = CascadeType.ALL)
    private Student student;
    @ManyToOne(cascade = CascadeType.ALL)
    private Course course;

    public StudentCourseId()
    {
    }

    public StudentCourseId(Student student, Course course)
    {
        this.course = course;
        this.student = student;
    }

    public Student getStudent()
    {
        return student;
    }

    public void setStudent(Student student)
    {
        this.student = student;
    }

    public Course getCourse()
    {
        return course;
    }

    public void setCourse(Course course)
    {
        this.course = course;
    }

    public boolean equals(Object studentCourseId)
    {
        if (this == studentCourseId)
        {
            return true;
        }

        if ((studentCourseId == null) || (this.getClass() != studentCourseId.getClass()))
        {
            return false;
        }

        StudentCourseId id = (StudentCourseId) studentCourseId;

        return Objects.equals(student, id.student) && Objects.equals(course, id.course);
    }

    public int hashCode()
    {
        return Objects.hash(student, course);
    }
}
