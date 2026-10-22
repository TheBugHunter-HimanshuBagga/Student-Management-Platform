package com.example.studentmanagement.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig { // converts DTO- Entity and Entity - DTO

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
