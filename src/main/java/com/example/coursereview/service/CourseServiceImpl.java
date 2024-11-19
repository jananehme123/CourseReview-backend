package com.example.coursereview.service;

import com.example.coursereview.model.Course;
import com.example.coursereview.model.Professor;
import com.example.coursereview.repository.CourseRepository;
import com.example.coursereview.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Configuration
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final ProfessorRepository professorRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository, ProfessorRepository professorRepository) {
        this.courseRepository = courseRepository;
        this.professorRepository =  professorRepository;
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Optional<Course> getCourseById(int id) {
        return courseRepository.findById(id);
    }

    @Override
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public void deleteCourse(int id) {
        courseRepository.deleteById(id);
    }

    @Override
    public List<Course> searchCourses(String keyword) {
        return courseRepository.searchCourses(keyword);
    }

    @Override
    public List<Course> getCoursesByIds(List<Integer> courseIds) {
        return courseRepository.findAllById(courseIds);
    }

    @Override
    public Course addProfessorToCourse(int courseId, int professorId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid course ID"));
        Professor professor = professorRepository.findById(professorId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid professor ID"));
        course.getProfessors().add(professor);
        return courseRepository.save(course);
    }
}



