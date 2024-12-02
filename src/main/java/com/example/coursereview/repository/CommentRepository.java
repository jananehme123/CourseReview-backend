package com.example.coursereview.repository;

import com.example.coursereview.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findByProfessorId(int professorId);
    //public Comment update(int id, Comment comment);
}