package com.example.restfulapi.controller;

import com.example.restfulapi.EmployeeNotFoundException;
import com.example.restfulapi.utils.Employee;
import com.example.restfulapi.model.EmployeeRepository;


import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class EmployeeController {
    private final EmployeeRepository repo;
    public EmployeeController(EmployeeRepository repo) {
        this.repo = repo;
    }


    @GetMapping("/employees")
    public CollectionModel<EntityModel<Employee>> all(){
        List<EntityModel<Employee>> employees = repo.findAll().stream()
                .map(employee -> EntityModel.of(employee,
                linkTo(methodOn(EmployeeController.class).one(employee.getId())).withSelfRel(),
                linkTo(methodOn(EmployeeController.class).all()).withRel("employees")))
                .collect(Collectors.toList());
        return CollectionModel.of(employees,
                linkTo(methodOn(EmployeeController.class).all()).withSelfRel());
    }

    @PostMapping("/employees")
    public EntityModel<Employee> newEmployee(@RequestBody Employee employee){
        Employee employee1 = repo.save(employee);
        return EntityModel.of(employee1,
                linkTo(methodOn(EmployeeController.class).newEmployee(employee)).withSelfRel(),
                linkTo(methodOn(EmployeeController.class).all()).withRel("employees"));
    }

    @GetMapping("/employees/{id}")
    public EntityModel<Employee> one(@PathVariable long id){
        Employee employee = repo.findById(id).orElseThrow(()-> new EmployeeNotFoundException(id));
        return EntityModel.of(employee,
                linkTo(methodOn(EmployeeController.class).one(id)).withSelfRel(),
                linkTo(methodOn(EmployeeController.class).all()).withRel("employees"));
    }

    @PutMapping("/employees/{id}")
    public Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable long id){
        return repo.findById(id).map(employee -> {
            employee.setName(newEmployee.getName());
            employee.setRole(newEmployee.getRole());
            return repo.save(employee);
        } ).orElseGet(() -> {
            newEmployee.setId(id);
            return  repo.save(newEmployee);
        });
    }

    @DeleteMapping("/employees/{id}")
    public void deleteEmployee(@PathVariable long id){
        repo.deleteById(id);
    }

}
