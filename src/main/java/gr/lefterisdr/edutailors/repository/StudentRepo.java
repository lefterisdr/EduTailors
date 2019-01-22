package gr.lefterisdr.edutailors.repository;

import gr.lefterisdr.edutailors.model.dao.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer>
{
}
