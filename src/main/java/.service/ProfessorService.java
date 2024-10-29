package com.example.coursereview.service;

import com.example.coursereview.model.Professor;
import java.util.List;
import java.util.Optional;

public interface ProfessorService {
    List<Professor> getAllProfessors();
    Optional<Professor> getProfessorById(int id);
    Professor saveProfessor(Professor professor);
    void deleteProfessor(int id);
}