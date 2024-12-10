package com.example.coursereview.controller;

import com.example.coursereview.model.Comment;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.hibernate.engine.spi.SessionDelegatorBaseImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.coursereview.model.Professor;
import com.example.coursereview.model.ProfessorRating;
import com.example.coursereview.model.Reply;
import com.example.coursereview.service.ProfessorService;
import com.example.coursereview.service.ReplyService;
import com.example.coursereview.service.CommentService;
import com.example.coursereview.repository.ProfessorRepository;
import com.example.coursereview.repository.ReplyRepository;
import com.example.coursereview.repository.CommentRepository;
import lombok.RequiredArgsConstructor; 

@RestController
@RequestMapping({"/comments"})
@RequiredArgsConstructor
public class CommentController {

   private final CommentService commentService;
   private final ReplyService replyService;


   @PutMapping({"/{id}"})
   public Comment updateComment(@PathVariable int id, @RequestBody Comment comment) throws ResourceNotFoundException {
      Optional<Comment> existingCommentOpt = commentService.getCommentById(id);
      if (existingCommentOpt.isPresent()) {
         Comment existingComment = existingCommentOpt.get();
         existingComment.setText(comment.getText());
         return commentService.saveComment(existingComment);
      } else {
         throw new ResourceNotFoundException("Comment not found with id " + id);
      }
   }

   @DeleteMapping({"/{id}"})
   public void deleteComment(@PathVariable int id) {
      this.commentService.deleteComment(id);
   }

   @GetMapping("/{id}/replies")
   public List<Reply> getRepliesByCommentId(@PathVariable int id) {
      return replyService.getRepliesByCommentId(id);
   }

   @PostMapping("/{id}/replies")
    public Reply addReplyToComment(@PathVariable int id, @RequestBody Reply reply) throws ResourceNotFoundException {
        return replyService.addReplyToComment(id, reply);
    }
}

