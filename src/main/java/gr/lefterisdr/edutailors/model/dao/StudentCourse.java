package gr.lefterisdr.edutailors.model.dao;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@Entity
@AssociationOverrides({
        @AssociationOverride(name = "studentCourseId.course", joinColumns = @JoinColumn(name = "course_id")),
        @AssociationOverride(name = "studentCourseId.student", joinColumns = @JoinColumn(name = "student_id"))})
public class StudentCourse //implements Serializable
{
    @EmbeddedId
    private StudentCourseId studentCourseId;
    //    @ManyToOne(fetch = FetchType.LAZY)
//    @MapsId("studentId")
//    private Student student;
//    @ManyToOne(fetch = FetchType.LAZY)
//    @MapsId("courseId")
//    private Course course;
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private Instant enrolmentDate;

    public StudentCourse()
    {
    }

    public StudentCourse(Student student, Course course)
    {
        studentCourseId = new StudentCourseId(student, course);

        enrolmentDate = Instant.now();
    }

    public StudentCourseId getStudentCourseId()
    {
        return studentCourseId;
    }

    public void setStudentCourseId(StudentCourseId studentCourseId)
    {
        this.studentCourseId = studentCourseId;
    }

    public Instant getEnrolmentDate()
    {
        return enrolmentDate;
    }

    public void setEnrolmentDate(Instant enrolmentDate)
    {
        this.enrolmentDate = enrolmentDate;
    }


    //    public boolean equals(Object studentCourse) {
//        if (this == studentCourse)
//        {
//            return true;
//        }
//
//        if ((studentCourse == null) || (this.getClass() != studentCourse.getClass()))
//        {
//            return false;
//        }
//
//        StudentCourse sc = (StudentCourse) studentCourse;
//
//        return Objects.equals(this.student, sc.student) && Objects.equals(this.course, sc.course);
//    }
//
//    public int hashCode() {
//        return Objects.hash(student, course);
//    }
}
