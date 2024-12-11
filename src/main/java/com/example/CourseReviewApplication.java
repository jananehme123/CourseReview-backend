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
        System.setProperty("PGHOST", Objects.requireNonNull(dotenv.get("PGHOST")));
        System.setProperty("PGPORT", Objects.requireNonNull(dotenv.get("PGPORT")));
        System.setProperty("PGDATABASE", Objects.requireNonNull(dotenv.get("PGDATABASE")));
        System.setProperty("PGUSER", Objects.requireNonNull(dotenv.get("PGUSER")));
        System.setProperty("PGPASSWORD", Objects.requireNonNull(dotenv.get("PGPASSWORD")));
        String activeProfile = System.getProperty("spring.profiles.active");
        if (activeProfile == null || activeProfile.isBlank()) {
            System.setProperty("spring.profiles.active", "dev");
        }
        SpringApplication.run(CourseReviewApplication.class, args);
    }
}