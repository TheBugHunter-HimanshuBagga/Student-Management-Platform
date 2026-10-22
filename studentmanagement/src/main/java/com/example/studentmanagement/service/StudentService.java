package com.example.studentmanagement.service;

import com.example.studentmanagement.dto.StudentRequestDTO;
import com.example.studentmanagement.dto.StudentResponseDTO;

import java.util.List;

public interface StudentService {

    // CREATE Student
    StudentResponseDTO createStudent(StudentRequestDTO request);

    // getAllStudents
    List<StudentResponseDTO> getAllStudents();

    // getStudentById
    StudentResponseDTO getStudentById(Long id);

    // updateStudent
    StudentResponseDTO updateStudent(
            Long id,
            StudentRequestDTO request
    );

    // Delete Student
    void deleteStudent(Long id);

}
