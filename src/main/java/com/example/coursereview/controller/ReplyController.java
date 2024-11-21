package com.example.coursereview.controller;

import com.example.coursereview.model.Reply;
import java.util.List;
import java.util.Optional;

import com.example.coursereview.service.ReplyService;
import com.example.coursereview.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/replies"})
public class ReplyController {

   @Autowired
   private ReplyService replyService;

   @Autowired
   private CommentService commentService;

   public ReplyController() {
   }

//    @PostMapping
//    public Reply addReply(@RequestBody Reply reply) {
//       return this.replyService.saveReply(reply);
//    }

   @PostMapping("/comment/{commentId}/reply")
   public Reply addReplyToComment (@PathVariable int commentId, @RequestBody Reply reply) throws ResourceNotFoundException{
    Optional<Comment> parentCommentOpt = commentService.getCommentById(commentId);
    if(parentCommentOpt.isPresent()){
        reply.setParentComment(parentCommentOpt.get());
        return replyService.saveReply(reply);
    } else{
        throw new ResourceNotFoundException ("Comment not found with id" + commentId);
    }
   }

   @GetMapping("/comment/{commentId}")
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
         return replyService.saveReply(existingReply);
      } else {
         throw new ResourceNotFoundException("Reply not found with id " + id);
      }
   }

   @DeleteMapping({"/{id}"})
   public void deleteReply(@PathVariable int id) {
      this.replyService.deleteReply(id);
   }
}
