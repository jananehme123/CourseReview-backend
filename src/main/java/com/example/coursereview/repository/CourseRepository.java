package com.example.coursereview.repository;

import com.example.coursereview.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
<<<<<<< HEAD:src/main/java/.repository/CourseRepository.java
    @Query("SELECT c FROM Course c WHERE c.name LIKE %:keyword% OR c.professor.name LIKE %:keyword% OR c.department.name LIKE %:keyword%")
    List<Course> searchCourses(@Param("keyword") String keyword);
}
=======
}
>>>>>>> 26f77adc2dadfba2add267c53f57c0dca3ee6354:src/main/java/com/example/coursereview/repository/CourseRepository.java
