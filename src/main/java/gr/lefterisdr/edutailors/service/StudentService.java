package gr.lefterisdr.edutailors.service;

import gr.lefterisdr.edutailors.exception.CourseNotFoundException;
import gr.lefterisdr.edutailors.exception.StudentAlreadyRegisteredAtCourseException;
import gr.lefterisdr.edutailors.exception.StudentNotFoundException;
import gr.lefterisdr.edutailors.exception.StudentNotRegisteredAtCourseException;
import gr.lefterisdr.edutailors.model.dao.Course;
import gr.lefterisdr.edutailors.model.dao.Student;
import gr.lefterisdr.edutailors.model.dao.StudentCourse;
import gr.lefterisdr.edutailors.repository.CourseRepo;
import gr.lefterisdr.edutailors.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class StudentService
{
    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private CourseRepo courseRepo;

    @Autowired
    private EntityManager entityManager;

    public List<Student> getEnrolledStudents(Integer courseId)
            throws CourseNotFoundException
    {
        Course course = courseRepo.findById(courseId).orElseThrow(CourseNotFoundException::new);

        return course.getCourseStudents().stream()
                .sorted((cs1, cs2) -> {
                    Student student1 = cs1.getStudentCourseId().getStudent();
                    Student student2 = cs2.getStudentCourseId().getStudent();

                    int lastnameComp = student1.getLastname().compareTo(student2.getLastname());

                    if (lastnameComp != 0)
                    {
                        return lastnameComp;
                    }

                    int firstnameComp = student1.getFirstname().compareTo(student2.getFirstname());

                    if (firstnameComp != 0)
                    {
                        return firstnameComp;
                    }

                    return cs1.getEnrolmentDate().compareTo(cs2.getEnrolmentDate());
                })
                .map(cs -> cs.getStudentCourseId().getStudent())
                .collect(Collectors.toList());
    }

    public Student getEnrolledStudent(Integer studentId, Integer courseId)
            throws StudentNotFoundException, CourseNotFoundException, StudentNotRegisteredAtCourseException
    {
        studentRepo.findById(studentId).orElseThrow(StudentNotFoundException::new);
        Course course = courseRepo.findById(courseId).orElseThrow(CourseNotFoundException::new);

        return course.getCourseStudents().stream()
                .map(sc -> sc.getStudentCourseId().getStudent())
                .filter(st -> st.getId() == studentId)
                .findAny()
                .orElseThrow(StudentNotRegisteredAtCourseException::new);
    }

    public Integer enrollStudent(Integer studentId, Integer courseId)
            throws StudentNotFoundException, CourseNotFoundException, StudentAlreadyRegisteredAtCourseException
    {
        Student student = studentRepo.findById(studentId).orElseThrow(StudentNotFoundException::new);
        Course course = courseRepo.findById(courseId).orElseThrow(CourseNotFoundException::new);

        if (this.isStudentRegisteredAtCourse(student, course))
        {
            throw new StudentAlreadyRegisteredAtCourseException();
        }

        StudentCourse studentCourse = new StudentCourse(student, course);

        course.getCourseStudents().add(studentCourse);

        return courseRepo.saveAndFlush(course).getId();
    }

    public void disenrollStudent(Integer studentId, Integer courseId)
            throws StudentNotFoundException, CourseNotFoundException, StudentNotRegisteredAtCourseException
    {
        Student student = studentRepo.findById(studentId).orElseThrow(StudentNotFoundException::new);
        Course course = courseRepo.findById(courseId).orElseThrow(CourseNotFoundException::new);

        if (! this.isStudentRegisteredAtCourse(student, course))
        {
            throw new StudentNotRegisteredAtCourseException();
        }

        StudentCourse studentCourse = new StudentCourse(student, course);

        course.getCourseStudents().remove(studentCourse);

        entityManager.getTransaction().commit();
    }

    private boolean isStudentRegisteredAtCourse(Student student, Course course)
            throws StudentNotFoundException, CourseNotFoundException
    {
        return course.getCourseStudents().stream()
                .map(sc -> sc.getStudentCourseId().getStudent())
                .anyMatch(st -> st.getId() == student.getId());
    }

}
