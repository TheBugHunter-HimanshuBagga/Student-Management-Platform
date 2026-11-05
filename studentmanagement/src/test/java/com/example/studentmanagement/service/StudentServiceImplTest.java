package com.example.studentmanagement.service;

import com.example.studentmanagement.dto.StudentRequestDTO;
import com.example.studentmanagement.dto.StudentResponseDTO;
import com.example.studentmanagement.entity.Student;
import com.example.studentmanagement.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class) // enables the Mockito support
public class StudentServiceImplTest {

    //  Unit testing Student Service Implementation

    /*
    Annotations:
        @Test -> marks a test method
        @Mock -> create a fake dependency
        @InjectMocks -> inject mock into the class
        @When -> define mock behaviour
     */

    @Mock
    private StudentRepository studentRepository; // fake repo created but does not connect with mysql or any database used

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private StudentServiceImpl studentService; // Actual service class being tested

    @Test
    void createStudent_shouldReturnSavedStudent(){

        StudentRequestDTO requestDTO = new StudentRequestDTO();
        requestDTO.setName("Himanshu Bagga");
        requestDTO.setEmail("himanshubagga@gmail.com");
        requestDTO.setAge(20);
        requestDTO.setCourse("C-TECH");

        Student savedStudent = new Student(
                1L,
                "Himanshu",
                "himanshubagga@gmail.com",
                20,
                "C-TECH"
        );

        StudentResponseDTO response = new StudentResponseDTO(
                1L,
                "Himanshu",
                "himanshubagga@gmail.com",
                20,
                "C-TECH"

        );

        when(studentRepository.save(any(Student.class))).thenReturn(savedStudent);

        when(modelMapper.map(savedStudent, StudentResponseDTO.class)).thenReturn(response);

        StudentResponseDTO result = studentService.createStudent(requestDTO);

        // Assert
        assertNotNull(result); // result should not be null
        assertEquals(1L, result.getId());
        assertEquals("Himanshu", result.getName());
        assertEquals("himanshubagga@gmail.com", result.getEmail());
        assertEquals(20, result.getAge());
        assertEquals("C-TECH", result.getCourse());

        verify(studentRepository, times(1)).save(any(Student.class));
    }


}
