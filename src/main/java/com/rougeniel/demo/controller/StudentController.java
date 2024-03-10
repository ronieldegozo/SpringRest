package com.rougeniel.demo.controller;

import com.rougeniel.demo.model.NotFoundException;
import com.rougeniel.demo.model.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentController {
    private List<Student> students;
    @PostConstruct
    public void loadData(){
        students = new ArrayList<>();

        students.add(new Student("Roniel","De Gozo"));
        students.add(new Student("Rogine","Laurito"));
    }

    @GetMapping("/students")
    public List<Student> getStudents(){

        return students;
    }

    //Retrieve Specific Student
    @GetMapping("/student/{studentId}")
    public Student getStudent(@PathVariable int studentId){

        if(studentId >= students.size() || studentId <0 ){
            throw new NotFoundException("Student ID not found : " + studentId);
        }

        return students.get(studentId);
    }

}
