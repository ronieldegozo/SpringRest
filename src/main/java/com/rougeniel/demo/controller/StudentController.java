package com.rougeniel.demo.controller;

import com.rougeniel.demo.model.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentController {

    @GetMapping("/students")
    public List<Student> getStudents(){

        List<Student> students = new ArrayList<>();

        students.add(new Student("Roniel","De Gozo"));
        students.add(new Student("Rogine","Laurito"));

        return students;
    }

}
