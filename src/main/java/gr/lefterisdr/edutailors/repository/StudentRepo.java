package gr.lefterisdr.edutailors.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import gr.lefterisdr.edutailors.model.dao.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer>
{
}
