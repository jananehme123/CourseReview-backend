package com.example.coursereview.service;

import com.example.coursereview.controller.ResourceNotFoundException;
import com.example.coursereview.model.Comment;
import com.example.coursereview.model.Professor;
import com.example.coursereview.repository.CommentRepository;
import com.example.coursereview.repository.ProfessorRepository;
import com.example.coursereview.utils.ProhibitedWordsFilter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;
    private final ProfessorRepository professorRepository;

    public CommentServiceImpl(CommentRepository commentRepository, ProfessorRepository professorRepository) {
        this.commentRepository = commentRepository;
        this.professorRepository = professorRepository;
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
        if (!professor.isPresent()) {
            throw new ResourceNotFoundException("Professor not found with id " + professorId);
        }
        comment.setProfessor(professor.get());
        return commentRepository.save(comment); 
    }

    @Override
    public Comment updateComment(int id, Comment comment) throws ResourceNotFoundException{
        if (ProhibitedWordsFilter.containsProhibitedWords(comment.getText())) {
            throw new IllegalStateException("Comment contains prohibited words");
        }
        Optional<Comment> existingCommentOpt = commentRepository.findById(id);
        if (!existingCommentOpt.isPresent()) {
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

//        Optional<Comment> commentOpt = getCommentById(id);
//        if (commentOpt.isPresent()) {
//            Comment comment = commentOpt.get();
//
//            List<Professor> professors = comment.getProfessor();
//            for (Professor professor : professors) {
//                professor.getComments().remove(comment);
//                professorService.saveComment(comment);
//            }
//            List<Reply> replies = replyRepository.findByParentCommentId(comment.getId());
//            for (Reply reply : replies) {
//                replyRepository.delete(reply);
//            }
//            comment.setComments(null);
//            comment.setReplies(null);
//            saveComment(comment);
//            commentRepository.delete(comment);
    }

}