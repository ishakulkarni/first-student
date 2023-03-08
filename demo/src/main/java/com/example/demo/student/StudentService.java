package com.example.demo.student;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.time.Month;

//Service layer
@Service    // can also use @Component
public class StudentService {
    private final StudentRepository studentRepository;
	
	@Autowired
	public StudentService(StudentRepository studentRepository){
		this.studentRepository = studentRepository;
	}

    public List<Student> getStudents(){

		return studentRepository.findAll();
		// return List.of(new Student(1L, "Harry", "harrypotter@hogwarts.edu", 
		// 	LocalDate.of(2023,Month.FEBRUARY,9), 11
		// ));
	}

	public void addNewStudent(Student student){
		Optional<Student> studentEmail = studentRepository
				.findStudentByEmail(student.getEmail());
		if(studentEmail.isPresent()){
			throw new IllegalStateException("email taken");
			
		}else{
			studentRepository.save(student);
		}
	}

	public void deleteStudent(Long id){
		if(!studentRepository.existsById(id)){
			throw new IllegalStateException(
				"student with id "+ id+" doesn't exist.");
		}
		
		studentRepository.deleteById(id);
	}

	@Transactional
	public void updateStudent(Long id, String name, String email){
		Student student = studentRepository.findById(id).orElseThrow(
			()-> new IllegalStateException(
				"student with id "+id+" doesn't exist."
			)
		);

		if(name!=null && name.length()>0 && !Objects.equals(student.getName(), name)){
				student.setName(name);
		}
		
		if(email!=null && email.length()>0 && Objects.equals(email, student.getEmail())){
			Optional<Student> studentEmail = studentRepository
				.findStudentByEmail(student.getEmail());
			if(studentEmail.isPresent()){
				throw new IllegalStateException("email taken");
			}
			student.setEmail(email);
		}
	}
}
