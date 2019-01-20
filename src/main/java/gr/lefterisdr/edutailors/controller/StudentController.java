package gr.lefterisdr.edutailors.controller;

import gr.lefterisdr.edutailors.exception.CourseNotFoundException;
import gr.lefterisdr.edutailors.exception.StudentAlreadyRegisteredAtCourseException;
import gr.lefterisdr.edutailors.exception.StudentNotFoundException;
import gr.lefterisdr.edutailors.exception.StudentNotRegisteredAtCourseException;
import gr.lefterisdr.edutailors.model.dao.Student;
import gr.lefterisdr.edutailors.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/student")
public class StudentController
{
    @Autowired
    private StudentService studentService;

    @GetMapping("/{courseId}")
    public List<Student> getEnrolledStudents(@PathVariable Integer courseId)
            throws CourseNotFoundException
    {
        return studentService.getEnrolledStudents(courseId);
    }

    @PostMapping("/{studentId}/{courseId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Integer enrollStudent(@PathVariable("studentId") Integer studentId, @PathVariable("courseId") Integer courseId)
            throws StudentNotFoundException, CourseNotFoundException, StudentAlreadyRegisteredAtCourseException
    {
        return studentService.enrollStudent(studentId, courseId);
    }

    @DeleteMapping("/{studentId}/{courseId}")
    public Integer disenrollStudent(@PathVariable("studentId") Integer studentId, @PathVariable("courseId") Integer courseId)
            throws StudentNotFoundException, CourseNotFoundException, StudentNotRegisteredAtCourseException
    {
        return studentService.disenrollStudent(studentId, courseId);
    }
}
