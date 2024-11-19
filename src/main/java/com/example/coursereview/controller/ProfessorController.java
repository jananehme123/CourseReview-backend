package com.example.coursereview.controller;

import com.example.coursereview.model.Course;
import com.example.coursereview.model.Professor;
import com.example.coursereview.model.ProfessorRating;
import com.example.coursereview.service.CourseService;
import com.example.coursereview.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/professors")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;
    @Autowired
    private CourseService courseService;

    @GetMapping
    public List<Professor> getAllProfessors() {
        return professorService.getAllProfessors();
    }

    @GetMapping("/{id}")
    public Optional<Professor> getProfessorById(@PathVariable int id) {
        return professorService.getProfessorById(id);
    }

    @PostMapping
    public Professor addProfessor(@RequestBody Professor professor) {
        List<Course> managedCourses = courseService.getCoursesByIds(
                professor.getCourses().stream().map(Course::getId).collect(Collectors.toList())
        );
        professor.setCourses(managedCourses);
        return professorService.saveProfessor(professor);
    }

    @PutMapping("/{id}")
    public Professor updateProfessor(@PathVariable int id, @RequestBody Professor professor) throws ResourceNotFoundException {
        Optional<Professor> existingProfessorOpt = professorService.getProfessorById(id);
        if (existingProfessorOpt.isPresent()) {
            Professor existingProfessor = existingProfessorOpt.get();
            existingProfessor.setFirstName(professor.getFirstName());
            existingProfessor.setLastName(professor.getLastName());
            List<Course> managedCourses = courseService.getCoursesByIds(
                    professor.getCourses().stream().map(Course::getId).collect(Collectors.toList())
            );
            existingProfessor.setCourses(managedCourses);
            return professorService.saveProfessor(existingProfessor);
        } else {
            throw new ResourceNotFoundException("Professor not found with id " + id);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteProfessor(@PathVariable int id) throws ResourceNotFoundException {
        professorService.deleteProfessor(id);
    }

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @GetMapping("/search")
    public List<Professor> searchProfessors(@RequestParam String keyword) {
        return professorService.searchProfessors(keyword);
    }

    @PostMapping("/rateProfessor")
    public ResponseEntity<String> rateProfessor(@RequestParam int professorId, @RequestParam int rating) {
        ProfessorRating professorRating = new ProfessorRating(0, professorId, rating);
        professorService.addRating(professorRating);
        return ResponseEntity.ok("Rating submitted successfully.");
    }

    @GetMapping("/{id}/ratings")
    public List<ProfessorRating> getRatingsByProfessorId(@PathVariable int id) {
        return professorService.getRatingsByProfessorId(id);
    }

    @GetMapping("/{id}/average-rating")
    public double getAverageRating(@PathVariable int id) {
        return professorService.calculateAverageRating(id);
    }
}
