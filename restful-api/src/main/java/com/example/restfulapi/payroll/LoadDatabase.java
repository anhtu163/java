package com.example.restfulapi.payroll;

import com.example.restfulapi.model.EmployeeRepository;
import com.example.restfulapi.utils.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
    private static Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(EmployeeRepository repo){
        return args -> {
            log.info("Preloading " + repo.save(new Employee("Huynh Anh", "dev")));
            log.info("Preloading " + repo.save(new Employee("Anh Tu", "tester")));
        };
    }
}
