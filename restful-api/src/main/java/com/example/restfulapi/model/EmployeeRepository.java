package com.example.restfulapi.model;

import com.example.restfulapi.utils.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
