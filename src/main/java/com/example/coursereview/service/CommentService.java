package com.example.coursereview.service;

import com.example.coursereview.model.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    List<Comment> getAllComments();
    Optional<Comment> getCommentById(int id);
    Comment saveComment(Comment comment);
    void deleteComment(int id);
}
