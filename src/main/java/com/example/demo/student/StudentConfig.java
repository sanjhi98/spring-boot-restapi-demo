package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.time.Month.DECEMBER;
import static java.time.Month.SEPTEMBER;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
           Student harry= new Student("Harry","harrystyles@gmail.com", LocalDate.of(1992, DECEMBER,31));
            Student niall= new Student("Niall","niallhoran@gmail.com", LocalDate.of(1993, SEPTEMBER,11));

            repository.saveAll(List.of(harry,niall));
        };
    }
}
