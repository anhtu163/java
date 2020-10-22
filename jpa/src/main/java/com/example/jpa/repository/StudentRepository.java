package com.example.jpa.repository;

import com.example.jpa.entity.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentRepository extends CrudRepository<Student, Long> {
    //CrudRepository là 1 interface chứa các phương thức hỗ trợ cơ bản các thao tác với đối tượng, lưu trữ, xóa, tìm kiếm
    List<Student> findByLastName(String lastName);

    Student findById(long id);

}
