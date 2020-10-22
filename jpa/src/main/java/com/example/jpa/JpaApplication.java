package com.example.jpa;

import com.example.jpa.entity.Student;
import com.example.jpa.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JpaApplication {

	private static final Logger log = LoggerFactory.getLogger(JpaApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(JpaApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(StudentRepository repository){
		return args -> {
			repository.save(new Student("Lee", "Loan"));
			repository.save(new Student("Seo", "Ben"));
			repository.save(new Student("Pack", "HSeo"));
			repository.save(new Student("Michel", "Jon"));

			// Hiển thị tât cả sinh viên
			System.out.println("Students found with findAll():");
			System.out.println("-------------------------------");
			for (Student Student : repository.findAll()) {
				System.out.println(Student.toString());
			}
			System.out.println("");

			// Hiển thị sinh viên theo ID
			Student Student = repository.findById(1L);
			System.out.println("Tìm sinh viên theo findById(1L):");
			System.out.println("--------------------------------");
			System.out.println(Student.toString());
			System.out.println("");

			// Hiển thị theo last name
			System.out.println("Tìm kiến theo findByLastName('Loan'):");
			System.out.println("--------------------------------------------");
			repository.findByLastName("Loan").forEach(name -> {
				System.out.println(name.toString());
			});
			// for (Student name : repository.findByLastName("Loan")) {
			//     System.out.println(name.toString());
			// }
			System.out.println("");
		};
	}
}
