package com.example.coursereview.service;

import com.example.coursereview.model.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    List<Comment> getAllCommentsForProfessor(int professorId);
    Optional<Comment> getCommentById(int id);
    Comment addComment(Comment comment);
    Comment updateComment (int id, Comment comment) throws ResourceNotFoundException;
    void deleteComment(int id);
}
