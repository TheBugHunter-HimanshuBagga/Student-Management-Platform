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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Test
    void getAllStudents_shouldReturnSavedStudent(){
        Student s1 = new Student(
                1L, "Himanshu" , "bagga@gmail.com" , 20 , "C-TECH"
        );

        Student s2 = new Student(
                2L, "Akshu" , "rome@gmail.com" , 19 , "BioTech"
        );

        List<Student> students = List.of(s1, s2);

        StudentResponseDTO dto1 = new StudentResponseDTO(
                1L, "Himanshu" , "bagga@gmail.com" , 20 , "C-TECH"
        );

        StudentResponseDTO dto2 = new StudentResponseDTO(
                2L, "Akshu" , "rome@gmail.com" , 19 , "BioTech"
        );

        when(studentRepository.findAll()).thenReturn(students);

        when(modelMapper.map(s1, StudentResponseDTO.class)).thenReturn(dto1);

        when(modelMapper.map(s2, StudentResponseDTO.class)).thenReturn(dto2);

        // ACT
        List<StudentResponseDTO> result = studentService.getAllStudents();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Himanshu", result.get(0).getName());
        assertEquals("bagga@gmail.com", result.get(0).getEmail());

        assertEquals("Akshu", result.get(1).getName());
        assertEquals("rome@gmail.com", result.get(1).getEmail());

        // Verify
        verify(studentRepository, times(1)).findAll(); // tells that this student repository find all method was called exactly oince

        verify(modelMapper, times(1)).map(s1, StudentResponseDTO.class);

        verify(modelMapper, times(1)). map(s2,StudentResponseDTO.class);
    }

    @Test
    void getStudentById_shouldReturnStudentWhenExists(){

        Student s1 = new Student(
                1L,
                "Himanshu",
                "himanshu@gmail.com",
                19,
                "CSE"
        );

        StudentResponseDTO responseDTO = new StudentResponseDTO(
                1L,
                "Himanshu",
                "himanshu@gmail.com",
                19,
                "CSE"
        );

        when(modelMapper.map(s1, StudentResponseDTO.class)).thenReturn(responseDTO);

        when(studentRepository.findById(1L)).thenReturn(Optional.of(s1));

        // ACT
        StudentResponseDTO result = studentService.getStudentById(1L);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Himanshu", result.getName());
        assertEquals("himanshu@gmail.com", result.getEmail());

        // verify
        verify(studentRepository, times(1)).findById(1L);
        verify(modelMapper, times(1)).map(s1, StudentResponseDTO.class);

    }



}
