package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository){
        return args -> {
            Student maria = (new Student(
                    "Maria",
                    "Yamaguchi",
                    "maria@gmail.com",
                    50));

            Student maria2 = (new Student(
                    "Maria",
                    "Yamaguchi",
                    "maria2@gmail.com",
                    25));

            Student joaquin = (new Student(
                    "Joaquin",
                    "Grandi",
                    "joaco@gmail.com",
                    30));

            studentRepository.saveAll(List.of(maria, maria2, joaquin));

            studentRepository
                    .findStudentByEmail("joaco@gmail.com")
                    .ifPresentOrElse(System.out::println, () -> System.out.println("Student with email not found"));

            studentRepository.findStudentsByFirstNameEqualsAndAgeEquals("Maria", 50).forEach(System.out::println);
            studentRepository.findStudentsByFirstNameEqualsAndAgeIsGreaterThan("Maria", 28).forEach(System.out::println);

        };
    }

}
