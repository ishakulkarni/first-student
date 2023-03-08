package com.example.demo.student;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//API layer - GET, POST, PUT, DELETE 
//Put- means update

@RestController
@RequestMapping(path="api/v1/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    //GET request
    @GetMapping
    public List<Student> getStudents(){
		return studentService.getStudents();
	}

    //POST request - check if email exists. 
    //if yes-throw exception, if not insert new entry
    @PostMapping
    public void registerNewStudent(@RequestBody Student student){
        studentService.addNewStudent(student);
    }

    //DELETE request
    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long id){
        studentService.deleteStudent(id);
    }

    //Put request - i.e. update
    @PutMapping 
    public void updateStudent(
        @PathVariable("studentId") Long id,
        @RequestParam(required = false) String name,
        @RequestParam(required=false) String email
    ){
        studentService.updateStudent(id,name,email);
    }

}
