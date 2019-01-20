package gr.lefterisdr.edutailors.model.dao;

import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * Created by Le on 20-Jan-19.
 */
public class StudentCourseId implements Serializable
{
    @ManyToOne(cascade = CascadeType.ALL)
    private Student student;
    @ManyToOne(cascade = CascadeType.ALL)
    private Course course;

    public StudentCourseId(Student student, Course course)
    {
        this.student = student;
        this.course = course;
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

    public boolean equals(Object studentCourseId) {
        if (this == studentCourseId)
        {
            return true;
        }

        if ((studentCourseId == null) || (this.getClass() != studentCourseId.getClass()))
        {
            return false;
        }

        StudentCourseId id = (StudentCourseId) studentCourseId;

        if (student != null ? !student.equals(id.getStudent()) : id.getStudent() != null)
        {
            return false;
        }

        return (course != null ? course.equals(id.getCourse()) : id.getCourse() == null);
    }

    public int hashCode() {
        int result = (student != null ? student.hashCode() : 0);

        return 31 * result + (course != null ? course.hashCode() : 0);
    }
}
