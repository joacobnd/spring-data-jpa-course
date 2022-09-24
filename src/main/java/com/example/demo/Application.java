package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.SQLOutput;
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
                    "Pele",
                    "maria@gmail.com",
                    50));

            Student maria2 = (new Student(
                    "Maria",
                    "Catunga",
                    "maria2@gmail.com",
                    25));

            Student joaquin = (new Student(
                    "Pedro",
                    "Sanchez",
                    "pedro@gmail.com",
                    40));
            studentRepository.saveAll(List.of(maria, maria2, joaquin));


            System.out.println("QUERY SOLO");
            studentRepository
                    .findStudentByEmail("pedro@gmail.com")
                    .ifPresentOrElse(System.out::println, () -> {
                        System.out.println("Student with email not found");
                    });



            studentRepository.findStudentsByFirstNameEqualsAndAgeEquals("Maria", 50).forEach(System.out::println);
            studentRepository.findStudentsByFirstNameEqualsAndAgeIsGreaterThan("Maria", 28).forEach(System.out::println);
            studentRepository.findStudentsByFirstNameEqualsAndAgeIsGreaterThanNative("Maria", 20).forEach(System.out::println);  //Native Query
        };
    }

}
