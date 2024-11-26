package com.example.coursereview.repository;

import com.example.coursereview.model.ProfessorRating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProfessorRatingRepository extends JpaRepository<ProfessorRating, Integer> {
    List<ProfessorRating> findByProfessorId(int professorId);
    Optional<ProfessorRating> findByProfessorIdAndUserId(int professorId, int userId);

}