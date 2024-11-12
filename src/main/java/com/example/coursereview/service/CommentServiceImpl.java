package com.example.coursereview.service;

import com.example.coursereview.model.Comment;
import com.example.coursereview.repository.CommentRepository;
import com.example.coursereview.utils.ProhibitedWordsFilter;
import java.util.List;
import java.util.Optional;


public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    @Override
    public Optional<Comment> getCommentById(int id) {
        return commentRepository.findById(id);
    }

    @Override
    public Comment saveComment(Comment comment) {
        if (ProhibitedWordsFilter.containsProhibitedWords(comment.getText())) {
            throw new IllegalStateException("Comment contains prohibited words");
        }
        return commentRepository.save(comment);
    }

    @Override
    public void deleteComment(int id) {
        commentRepository.deleteById(id);
    }

}
