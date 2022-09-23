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

            Student joaquin = (new Student(
                    "Joaquin",
                    "GRandi",
                    "joaco@gmail.com",
                    30));

            System.out.println("Adding Maria and Joaquin");
            studentRepository.saveAll(List.of(maria,joaquin));

            System.out.print("Number of students");
            System.out.println(studentRepository.count());

            studentRepository
                    .findById(2L)
                    .ifPresentOrElse(System.out::println,
                            () -> System.out.println("Student with ID 2 not found"));

            studentRepository
                    .findById(3L)
                    .ifPresentOrElse(System.out::println,
                            () -> System.out.println("Student with ID 3 not found"));

            System.out.println("Select all students");
            List<Student> student = studentRepository.findAll();
            student.forEach(System.out::println);

            System.out.println("Delete Maria students");
            studentRepository.deleteById(1L);

            System.out.println("Numbers of students");
            System.out.println(studentRepository.count());
        };
    }

}
