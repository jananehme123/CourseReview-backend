package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.example.coursereview.model")
@EnableJpaRepositories("com.example.coursereview.repository")
@ComponentScan(basePackages = {"com.example", "com.example.coursereview"})
public class CourseReviewApplication {
    public static void main(String[] args) {
        SpringApplication.run(CourseReviewApplication.class, args);
    }
}