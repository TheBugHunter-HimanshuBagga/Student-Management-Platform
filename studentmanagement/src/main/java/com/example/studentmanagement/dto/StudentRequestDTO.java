package com.example.studentmanagement.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentRequestDTO { // what information we need from teh user

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Enter a Valid Email")
    private String email;

    @NotBlank(message = "Age is required")
    @Min(value = 1 , message = "Age must be greater than zero")
    private Integer age;

    @NotBlank(message = "Course is required")
    private String course;

}
