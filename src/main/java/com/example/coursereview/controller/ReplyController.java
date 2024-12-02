package com.example.coursereview.controller;

import com.example.coursereview.model.*;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.hibernate.engine.spi.SessionDelegatorBaseImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.coursereview.model.Reply;
import com.example.coursereview.service.ProfessorService;
import com.example.coursereview.service.ReplyService;
import com.example.coursereview.service.CommentService;
import com.example.coursereview.repository.ProfessorRepository;
import com.example.coursereview.repository.ReplyRepository;
import com.example.coursereview.repository.CommentRepository;
import lombok.RequiredArgsConstructor; 

@RestController
@RequestMapping({"/replies"})
public class ReplyController {

   private final ReplyService replyService;
   private final CommentService commentService;

   public ReplyController(ReplyService replyService, CommentService commentService) {
       this.replyService = replyService;
       this.commentService = commentService;
   }

   @PostMapping("/comment/{commentId}/replies")
   public Reply addReply (@PathVariable int commentId, @RequestBody Reply reply) throws ResourceNotFoundException{
    Optional<Comment> parentCommentOpt = commentService.getCommentById(commentId);
    if(parentCommentOpt.isPresent()){
        reply.setParentComment(parentCommentOpt.get());
        return replyService.addReplyToComment(commentId, reply);
    } else{
        throw new ResourceNotFoundException ("Comment not found with id" + commentId);
    }
   }

   @GetMapping("/{commentId}/replies")
    public List<Reply> getRepliesByCommentId(@PathVariable int commentId) {
        return replyService.getRepliesByCommentId(commentId);
    }

   @GetMapping
   public List<Reply> getAllReplies() {
      return this.replyService.getAllReplies();
   }

   @GetMapping({"/{id}"})
   public Optional<Reply> getReplyById(@PathVariable int id) {
      return this.replyService.getReplyById(id);
   }

 
   @PutMapping({"/{id}"})
   public Reply updateReply(@PathVariable int id, @RequestBody Reply reply) throws ResourceNotFoundException {
      Optional<Reply> existingReplyOpt = replyService.getReplyById(id);
      if (existingReplyOpt.isPresent()) {
         Reply existingReply = existingReplyOpt.get();
         existingReply.setText(reply.getText());
         return replyService.updateReply(existingReply);
      } else {
         throw new ResourceNotFoundException("Reply not found with id " + id);
      }
   }

   @DeleteMapping({"/{id}"})
   public void deleteReply(@PathVariable int id) {
      this.replyService.deleteReply(id);
   }
}