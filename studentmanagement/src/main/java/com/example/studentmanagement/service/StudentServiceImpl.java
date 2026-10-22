package com.example.studentmanagement.service;

import com.example.studentmanagement.dto.StudentRequestDTO;
import com.example.studentmanagement.dto.StudentResponseDTO;
import com.example.studentmanagement.entity.Student;
import com.example.studentmanagement.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor // for constructor injection
public class StudentServiceImpl implements StudentService{ // Business Logic

    private final StudentRepository studentRepository; // constructor injection
    private final ModelMapper modelMapper;

    @Override
    public StudentResponseDTO createStudent(StudentRequestDTO request){
        Student student = new Student();

        student.setName(request.getName());
        student.setAge(request.getAge());
        student.setEmail(request.getEmail());
        student.setCourse(request.getCourse());

        Student savedStudent = studentRepository.save(student);

        return modelMapper.map(savedStudent, StudentResponseDTO.class);
    }

    @Override
    public List<StudentResponseDTO> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream()
                .map(student -> modelMapper.map(student, StudentResponseDTO.class))
                .toList();
    }

    @Override
    public StudentResponseDTO getStudentById(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Student not found with ID: " + id)
        );
        return modelMapper.map(student, StudentResponseDTO.class);
    }

    @Override
    public StudentResponseDTO updateStudent(Long id, StudentRequestDTO request) {
        Student student = studentRepository
                .findById(id)
                .orElseThrow(
                        () -> new RuntimeException("Student not found with ID: \" + id")
                );

        student.setName(request.getName());
        student.setAge(request.getAge());
        student.setEmail(request.getEmail());
        student.setCourse(request.getCourse());

        Student savedStudent = studentRepository.save(student);
        return modelMapper.map(savedStudent, StudentResponseDTO.class);
    }

    @Override
    public void deleteStudent(Long id) {
        if(!studentRepository.existsById(id)){
            throw new RuntimeException(
                    "Student with id: " + id + " doesn't exists"
             );
        }
        studentRepository.deleteById(id);
    }
}
