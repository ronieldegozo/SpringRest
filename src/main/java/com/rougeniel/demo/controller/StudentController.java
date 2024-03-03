package com.rougeniel.demo.controller;

import com.rougeniel.demo.model.ErrorResponse;
import com.rougeniel.demo.model.NotFoundException;
import com.rougeniel.demo.model.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(NotFoundException ex){

        ErrorResponse error = new ErrorResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(ex.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    //Handle all exception
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(Exception ex){

        ErrorResponse error = new ErrorResponse();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(ex.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
