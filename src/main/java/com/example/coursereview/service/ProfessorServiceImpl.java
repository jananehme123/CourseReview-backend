package com.example.coursereview.service;

import com.example.coursereview.controller.ResourceNotFoundException;
import com.example.coursereview.model.Course;
import com.example.coursereview.model.Professor;
import com.example.coursereview.model.ProfessorRating;
import com.example.coursereview.repository.ProfessorRatingRepository;
import com.example.coursereview.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfessorServiceImpl implements ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;
    private ProfessorService professorService;
    private CourseService courseService;
    @Autowired
    private ProfessorRatingRepository professorRatingRepository;

    @Override
    public List<Professor> getAllProfessors() {
        return professorRepository.findAll();
    }

    @Override
    public Optional<Professor> getProfessorById(int id) {
        return professorRepository.findById(id);
    }


    @Override
    public List<Professor> getProfessorsByIds(List<Integer> professorIds) {
        return professorRepository.findAllById(professorIds);
    }

    @Override
    public Professor saveProfessor(Professor professor) {
        return professorRepository.save(professor);
    }

    @Override
    public void deleteProfessor(int id) throws ResourceNotFoundException {
        Optional<Professor> professorOpt = professorService.getProfessorById(id);
        if (professorOpt.isPresent()) {
            Professor professor = professorOpt.get();

            List<Course> courses = professor.getCourses();
            for (Course course : courses) {
                course.getProfessors().remove(professor);
                courseService.saveCourse(course);
            }
            professor.setCourses(null);
            professorService.saveProfessor(professor);
            professorService.deleteProfessor(id);
        } else {
            throw new ResourceNotFoundException("Professor not found with id " + id);
        }
    }

    public ProfessorServiceImpl(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    @Override
    public List<Professor> searchProfessors(String keyword) {
        return professorRepository.searchProfessors(keyword);
    }


   private List<ProfessorRating> ratings = new ArrayList<>();


    // Rating Methods
    @Override
    public void addRating(ProfessorRating rating) {
        professorRatingRepository.save(rating);
    }

    @Override
    public double calculateAverageRating(int professorId) {
        List<ProfessorRating> ratings = professorRatingRepository.findByProfessorId(professorId);
        return ratings.stream().mapToInt(ProfessorRating::getRating).average().orElse(0.0);
    }

    @Override
    public List<ProfessorRating> getRatingsByProfessorId(int professorId) {
        return professorRatingRepository.findByProfessorId(professorId);
    }
}
