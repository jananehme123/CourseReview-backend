package com.example.coursereview.service;

import com.example.coursereview.model.Professor;
import java.util.List;
import java.util.Optional;

public interface ProfessorService {
    List<Professor> getAllProfessors();
    Optional<Professor> getProfessorById(int id);
    Professor saveProfessor(Professor professor);
    void deleteProfessor(int id);
    List<Professor> searchProfessors(String keyword);

public interface ProfessorService {
    List<Professor> getAllProfessors();
    Optional<Professor> getProfessorById(int id);
    Professor saveProfessor(Professor professor);
    void deleteProfessor(int id);
    List<Professor> searchProfessors(String keyword);
    
    // Optional: Add these if you want them accessible through the interface
    void addRating(ProfessorRating rating);
    double calculateAverageRating(int professorId);
}





    
}
