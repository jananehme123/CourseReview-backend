package com.example.coursereview.service;

import com.example.coursereview.controller.ResourceNotFoundException;
import com.example.coursereview.model.Professor;
import com.example.coursereview.model.ProfessorRating;

import java.util.List;
import java.util.Optional;

public interface ProfessorService {
    List<Professor> getAllProfessors();
    Optional<Professor> getProfessorById(int id);
    Professor saveProfessor(Professor professor);
    void deleteProfessor(int id) throws ResourceNotFoundException;
    List<Professor> searchProfessors(String keyword);

    void addRating(ProfessorRating rating);
    double calculateAverageRating(int professorId);
    List<ProfessorRating> getRatingsByProfessorId(int professorId);
    List<Professor> getProfessorsByIds(List<Integer> professorIds);
}

