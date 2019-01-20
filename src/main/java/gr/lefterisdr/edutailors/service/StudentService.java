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

import java.util.List;
import java.util.stream.Collectors;


@Service
public class StudentService
{
    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private CourseRepo courseRepo;

    public List<Student> getEnrolledStudents(Integer courseId)
            throws CourseNotFoundException
    {
        Course course = courseRepo.findById(courseId).orElseThrow(CourseNotFoundException::new);

        return course.getCourseStudents().stream()
                .sorted((cs1, cs2) -> {
                    Student student1 = cs1.getPk().getStudent();
                    Student student2 = cs2.getPk().getStudent();

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
                .map(cs -> cs.getPk().getStudent())
                .collect(Collectors.toList());
    }

    public Integer enrollStudent(Integer studentId, Integer courseId)
            throws StudentNotFoundException, CourseNotFoundException, StudentAlreadyRegisteredAtCourseException
    {
        Student student = studentRepo.findById(studentId).orElseThrow(StudentNotFoundException::new);
        Course course = courseRepo.findById(courseId).orElseThrow(CourseNotFoundException::new);

        if (student.getStudentCourses().stream().map(sc -> sc.getPk().getCourse()).anyMatch(c -> c.getId() == courseId))
        {
            throw new StudentAlreadyRegisteredAtCourseException();
        }

        StudentCourse studentCourse = new StudentCourse(student, course);

        student.getStudentCourses().add(studentCourse);

        return studentRepo.saveAndFlush(student).getId();
    }

    public Integer disenrollStudent(Integer studentId, Integer courseId)
            throws StudentNotFoundException, CourseNotFoundException, StudentNotRegisteredAtCourseException
    {
        Student student = studentRepo.findById(studentId).orElseThrow(StudentNotFoundException::new);
        Course course = courseRepo.findById(courseId).orElseThrow(CourseNotFoundException::new);

        student.getStudentCourses().stream()
                .map(sc -> sc.getPk().getCourse())
                .filter(c -> c.getId() == courseId)
                .findAny().orElseThrow(StudentNotRegisteredAtCourseException::new);

        StudentCourse studentCourse = new StudentCourse(student, course);

        student.getStudentCourses().remove(studentCourse);

        return studentRepo.saveAndFlush(student).getId();
    }
}
