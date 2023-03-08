package com.example.demo.student;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
//data access layer
//interface that accesses database
public interface StudentRepository extends JpaRepository<Student, Long>{
    

    //SELECT * from student WHERE email= what we passed
    //@Query("SELECT s FROM Student s WHERE s.email = ?1")

    Optional<Student> findStudentByEmail(String email);
}
