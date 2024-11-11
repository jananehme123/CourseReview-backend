package com.example.coursereview.service;

import com.example.coursereview.model.Course;
import java.util.List;
import java.util.Optional;

public interface CourseService {
    List<Course> getAllCourses();
    Optional<Course> getCourseById(int id);
    Course saveCourse(Course course);
    void deleteCourse(int id);
    List<Course> searchCourses(String keyword);
}
