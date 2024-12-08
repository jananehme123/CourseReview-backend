package com.example;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.Objects;

@SpringBootApplication
@EntityScan("com.example.coursereview.model")
@EnableJpaRepositories("com.example.coursereview.repository")
@ComponentScan(basePackages = {"com.example", "com.example.coursereview"})
@EnableAsync(proxyTargetClass = true)
public class CourseReviewApplication {
    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();
        System.setProperty("SPRING_MAIL_USERNAME", Objects.requireNonNull(dotenv.get("SPRING_MAIL_USERNAME")));
        System.setProperty("SPRING_MAIL_PASSWORD", Objects.requireNonNull(dotenv.get("SPRING_MAIL_PASSWORD")));
        SpringApplication.run(CourseReviewApplication.class, args);
    }
}