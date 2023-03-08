package com.example.demo.student;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.CommandLineRunner;
import java.util.*;
import java.time.LocalDate;
import java.time.Month;

@Configuration
public class StudentConfig {

    StudentService stu;
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args ->{
            Student harry = new Student(1L, "Harry", "harrypotter@hogwarts.edu", 
                                LocalDate.of(2011,Month.FEBRUARY,9));
	    repository.save(harry);
            
		/*Student ron = new Student(2L, "Ron", "ronald@hogwarts.edu", 
                                LocalDate.of(2012,Month.MARCH,9));
        	*/

            //both add -ron- repeatitivelywithout checking if student exists
            //repository.saveAll(List.of(harry, ron)); 
            
	    //stu.addNewStudent(harry);
            //stu.addNewStudent(ron);

            };
    }
}
