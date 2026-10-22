package com.example.studentmanagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity // tells the spring application to to create a table inside the database using Hibernate which is an ORM(Object Relational Mapping) which helps creates table
@Setter
@Getter // Lombok generates getters and setters.
@Table(name = "students") // gives table name as that
@AllArgsConstructor// Generates a constructor with all fields.
@NoArgsConstructor //Generates a no-argument constructor.
public class Student {

    @Id // Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 1,2,3,4,5,......n Generates IDs automatically.
    private Long id;

    @Column(nullable = false) // Makes the column mandatory. constraints
    private String name;

    @Column(unique = true) // Prevents duplicate email values at the database level.
    private String email;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false)
    private String course;

}
