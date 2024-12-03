package com.example.coursereview.service;

import com.example.coursereview.controller.ResourceNotFoundException;
import com.example.coursereview.model.Comment;
import com.example.coursereview.model.Professor;
import com.example.coursereview.model.User;
import com.example.coursereview.repository.CommentRepository;
import com.example.coursereview.repository.ProfessorRepository;
import com.example.coursereview.repository.UserRepository;
import com.example.coursereview.utils.ProhibitedWordsFilter;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;
    private final ProfessorRepository professorRepository;
    private final UserRepository userRepository;

    public CommentServiceImpl(CommentRepository commentRepository, ProfessorRepository professorRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.professorRepository = professorRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Comment> getAllCommentsForProfessor(int professorId) {
        return commentRepository.findByProfessorId(professorId);
    }

    @Override
    public Optional<Comment> getCommentById(int id) {
        return commentRepository.findById(id);
    }

    @Override
    public Comment addComment(int professorId, Comment comment) throws ResourceNotFoundException {
        if (ProhibitedWordsFilter.containsProhibitedWords(comment.getText())) {
            throw new IllegalStateException("Comment contains prohibited words");
        }
        Optional<Professor> professor = professorRepository.findById(professorId);
        if (professor.isEmpty()) {
            throw new ResourceNotFoundException("Professor not found with id " + professorId);
        }

        if (comment.getUser() == null || comment.getUser().getId() == 0) {
            throw new IllegalStateException("User ID is required");
        }
        Optional<User> user = userRepository.findById(comment.getUser().getId());
        if (user.isEmpty()) {
            throw new ResourceNotFoundException("User not found with id " + comment.getUser().getId());
        }
        comment.setProfessor(professor.get());
        comment.setUser(user.get());
        return commentRepository.save(comment);
    }

    @Override
    public Comment updateComment(int id, Comment comment) throws ResourceNotFoundException{
        if (ProhibitedWordsFilter.containsProhibitedWords(comment.getText())) {
            throw new IllegalStateException("Comment contains prohibited words");
        }
        Optional<Comment> existingCommentOpt = commentRepository.findById(id);
        if (existingCommentOpt.isEmpty()) {
            throw new ResourceNotFoundException("Comment not found with id " + id);
        }
        Comment existingComment = existingCommentOpt.get();
        existingComment.setText(comment.getText());
        return commentRepository.save(existingComment);
    }

    @Override
    public Comment saveComment(Comment comment){
        return commentRepository.save(comment);
    }

    @Override
    public void deleteComment(int id) {
        commentRepository.deleteById(id);
    }

}
