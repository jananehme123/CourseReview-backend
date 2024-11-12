package com.example.coursereview.repository;

import com.example.coursereview.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
import java.util.List;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Integer> {
    @Query("SELECT p FROM Professor p WHERE p.firstName LIKE %:keyword% OR p.lastName LIKE %:keyword% OR p.department.name LIKE %:keyword%")
    List<Professor> searchProfessors(@Param("keyword") String keyword);
}
