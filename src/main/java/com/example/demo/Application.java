package com.example.demo;

import com.github.javafaker.Faker;
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
            Faker faker = new Faker();
            for (int i = 0; i < 20; i++) {
                String firstName = faker.name().firstName();
                String lastName = faker.name().lastName();
                String email = String.format("%s.%s@amigoscode.edu", firstName, lastName);
                Student student = new Student(
                        firstName,
                        lastName,
                        email,
                        faker.number().numberBetween(17, 55));
                studentRepository.save(student);
            }
        };
    }

}
