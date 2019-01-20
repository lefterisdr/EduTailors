package gr.lefterisdr.edutailors.controller;

import gr.lefterisdr.edutailors.exception.CourseNotFoundException;
import gr.lefterisdr.edutailors.exception.StudentNotFoundException;
import gr.lefterisdr.edutailors.model.dao.Course;
import gr.lefterisdr.edutailors.model.dao.Student;
import gr.lefterisdr.edutailors.repository.CourseRepo;
import gr.lefterisdr.edutailors.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/student")
public class StudentController
{
    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private CourseRepo courseRepo;

    @GetMapping
    public List<Student> getStudents(@PathVariable Integer courseId)
            throws CourseNotFoundException
    {
        Course course = courseRepo.findById(courseId).orElseThrow(CourseNotFoundException::new);

        return studentRepo.findAll().stream().filter(stud -> stud.getStudentCourses().contains(course)).collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer createStudent(@RequestBody Student student)
    {
        return studentRepo.saveAndFlush(student).getId();
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable("id") Integer id)
            throws StudentNotFoundException
    {
        Student existingStudent = studentRepo.findById(id).orElseThrow(StudentNotFoundException::new);
        studentRepo.delete(existingStudent);
    }
}
