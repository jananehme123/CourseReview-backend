package com.example.coursereview.repository;

import com.example.coursereview.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
    @Query("SELECT c FROM Course c WHERE c.name LIKE %:keyword% OR c.professor.name LIKE %:keyword% OR c.department.name LIKE %:keyword%")
    List<Course> searchCourses(@Param("keyword") String keyword);
}

