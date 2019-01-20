package gr.lefterisdr.edutailors.repository;

import gr.lefterisdr.edutailors.model.dao.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepo extends JpaRepository<Course, Integer>
{
}
