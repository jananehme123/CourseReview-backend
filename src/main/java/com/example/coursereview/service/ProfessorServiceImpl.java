package com.example.coursereview.service;

import com.example.coursereview.model.Professor;
import com.example.coursereview.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorServiceImpl implements ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    @Override
    public List<Professor> getAllProfessors() {
        return professorRepository.findAll();
    }

    @Override
    public Optional<Professor> getProfessorById(int id) {
        return professorRepository.findById(id);
    }

    @Override
    public Professor saveProfessor(Professor professor) {
        return professorRepository.save(professor);
    }

    @Override
    public void deleteProfessor(int id) {
        professorRepository.deleteById(id);
    }

    public ProfessorServiceImpl(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    @Override
    public List<Professor> searchProfessors(String keyword) {
        return professorRepository.searchProfessors(keyword);
    }


public class ProfessorServiceImpl implements ProfessorService {

    private List<ProfessorRating> ratings = new ArrayList<>();

    @Override
    public List<Professor> getAllProfessors() {
      
    }

    @Override
    public Optional<Professor> getProfessorById(int id) {
       
    }

    @Override
    public Professor saveProfessor(Professor professor) {
      
    }

    @Override
    public void deleteProfessor(int id) {
     
    }

    @Override
    public List<Professor> searchProfessors(String keyword) {
       
    }

    // Rating Methods
    public void addRating(ProfessorRating rating) {
        ratings.add(rating);
    }

    public double calculateAverageRating(int professorId) {
        int sum = 0;
        int count = 0;
        for (ProfessorRating rating : ratings) {
            if (rating.getProfessorId() == professorId) {
                sum += rating.getRating();
                count++;
            }
        }
        return count == 0 ? 0 : (double) sum / count;
    }
}
    
}
