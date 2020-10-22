package com.example.jpa.controller;

import com.example.jpa.entity.Student;
import com.example.jpa.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    StudentRepository repo;
    @GetMapping
    public List<Student> getStudents(){
        return (List<Student>) repo.findAll();
    }
    @PostMapping
    public void postStudent(@RequestBody Student dto){
        repo.save(dto);
    }
    @GetMapping("/{id}")
    public Student getById(@PathVariable(required = true) long id){
        return repo.findById(id);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable(required = true) long id){
        repo.deleteById(id);
    }
}
