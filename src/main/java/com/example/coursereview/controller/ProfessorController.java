package com.example.coursereview.controller;

import com.example.coursereview.model.Professor;
import com.example.coursereview.model.ProfessorRating;
import com.example.coursereview.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/professors")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

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
        return professorService.saveProfessor(professor);
    }

    @PutMapping("/{id}")
    public Professor updateProfessor(@PathVariable int id, @RequestBody Professor professor) {
        professor.setFirstName(professor.getFirstName());
        professor.setLastName(professor.getLastName());
        return professorService.saveProfessor(professor);
    }

    @DeleteMapping("/{id}")
    public void deleteProfessor(@PathVariable int id) {
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
public ResponseEntity<String> rateProfessor(@RequestParam int professorId, @RequestParam int rating, @RequestParam String review) {
    ProfessorRating professorRating = new ProfessorRating(0, professorId, rating, review);
    professorService.addRating(professorRating);
    return ResponseEntity.ok("Rating submitted successfully.");
}








    
}
