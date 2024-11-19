package com.example.coursereview.controller;

import com.example.coursereview.model.Course;
import com.example.coursereview.model.Professor;
import com.example.coursereview.service.CourseService;
import com.example.coursereview.service.ProfessorService;
import jakarta.persistence.ManyToMany;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;
    @Autowired
    private ProfessorService professorService;

    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/{id}")
    public Optional<Course> getCourseById(@PathVariable int id) {
        return courseService.getCourseById(id);
    }

    @PostMapping
    public Course addCourse(@RequestBody Course course) {
        return courseService.saveCourse(course);
    }

    @PutMapping("/{id}")
    public Course updateCourse(@PathVariable int id, @RequestBody Course course) throws ResourceNotFoundException {
        Optional<Course> existingCourseOpt = courseService.getCourseById(id);
        if (existingCourseOpt.isPresent()) {
            Course existingCourse = existingCourseOpt.get();
            existingCourse.setCode(course.getCode());
            existingCourse.setNumber(course.getNumber());
            existingCourse.setName(course.getName());
            existingCourse.setDepartment(course.getDepartment());
            existingCourse.setProfessors(course.getProfessors());
            return courseService.saveCourse(existingCourse);
        } else {
            throw new ResourceNotFoundException("Course not found with id " + id);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable int id) {
        courseService.deleteCourse(id);
    }

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/search")
    public List<Course> searchCourses(@RequestParam String keyword) {
        return courseService.searchCourses(keyword);
    }

    @GetMapping("/{id}/professors")
    public List<Professor> getProfessorsByCourseId(@PathVariable int id) {
        Optional<Course> course = courseService.getCourseById(id);
        return course.map(Course::getProfessors).orElse(Collections.emptyList());
    }

    @Transactional
    @PutMapping("/{id}/professors")
    public ResponseEntity<Course> linkProfessorsToCourse(@PathVariable int id, @RequestBody int professorId) throws ResourceNotFoundException {
        return ResponseEntity.ok(courseService.addProfessorToCourse(id, professorId));
    }
}