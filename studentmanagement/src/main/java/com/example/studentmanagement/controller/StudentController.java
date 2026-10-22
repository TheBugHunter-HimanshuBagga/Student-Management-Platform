package com.example.studentmanagement.controller;

import com.example.studentmanagement.dto.StudentRequestDTO;
import com.example.studentmanagement.dto.StudentResponseDTO;
import com.example.studentmanagement.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;

    // Add student
    @PostMapping
    public ResponseEntity<StudentResponseDTO> createStudent(@Valid @RequestBody StudentRequestDTO request){
        StudentResponseDTO response = studentService.createStudent(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // GetAll Students
    @GetMapping
    public ResponseEntity<List<StudentResponseDTO>> getAllStudents(){
        List<StudentResponseDTO> responseList = studentService.getAllStudents();
        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> getStudentById(@PathVariable Long id){
        StudentResponseDTO response = studentService.getStudentById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("{id}")
    public ResponseEntity<StudentResponseDTO> updateStudent(@PathVariable Long id , @RequestBody StudentRequestDTO request){
        StudentResponseDTO response = studentService.updateStudent(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);
    }

}
