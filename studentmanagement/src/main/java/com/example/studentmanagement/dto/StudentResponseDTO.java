package com.example.studentmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponseDTO { // what we wants tro return to the user
    private Long id;
    private String name;
    private String email;
    private Integer age;
    private String course;
}
