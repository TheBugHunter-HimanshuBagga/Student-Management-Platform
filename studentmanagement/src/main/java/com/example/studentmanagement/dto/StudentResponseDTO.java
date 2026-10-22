package com.example.studentmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StudentResponseDTO { // what we wants tro return to the user
    private Long id;
    private String name;
    private String email;
    private String age;
    private String course;
}
