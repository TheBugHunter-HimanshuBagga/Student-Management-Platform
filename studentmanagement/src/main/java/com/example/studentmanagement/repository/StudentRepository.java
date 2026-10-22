package com.example.studentmanagement.repository;

import com.example.studentmanagement.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long>{
    // manages Student entities whose primary key type is Long.
    // interface — defines the repository contract; Spring creates its implementation at runtime.
}
