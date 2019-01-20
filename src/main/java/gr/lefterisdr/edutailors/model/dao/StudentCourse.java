package gr.lefterisdr.edutailors.model.dao;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Entity
@AssociationOverrides({
        @AssociationOverride(name="pk.course", joinColumns = @JoinColumn(name="course_id")),
        @AssociationOverride(name="pk.student", joinColumns = @JoinColumn(name="student_id"))})
public class StudentCourse implements Serializable
{
    @EmbeddedId
    private StudentCourseId pk;
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private Date enrolmentDate;

    public StudentCourse(Student student, Course course)
    {
        pk = new StudentCourseId(student, course);
        enrolmentDate = new Date(LocalDate.now().toEpochDay());
    }

    public StudentCourseId getPk()
    {
        return pk;
    }

    public void setPk(StudentCourseId pk)
    {
        this.pk = pk;
    }

    public Date getEnrolmentDate()
    {
        return enrolmentDate;
    }

    public void setEnrolmentDate(Date enrolmentDate)
    {
        this.enrolmentDate = enrolmentDate;
    }

    public boolean equals(Object studentCourse) {
        if (this == studentCourse)
        {
            return true;
        }

        if ((studentCourse == null) || (this.getClass() != studentCourse.getClass()))
        {
            return false;
        }

        StudentCourse sc = (StudentCourse) studentCourse;

        return (pk != null ? pk.equals(sc.getPk()) : sc.getPk() == null);
    }

    public int hashCode() {
        return (pk != null ? pk.hashCode() : 0);
    }
}
