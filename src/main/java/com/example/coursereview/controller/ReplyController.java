package com.example.coursereview.controller;

import java.util.Optional;

import org.springframework.web.bind.annotation.*;
import com.example.coursereview.model.Reply;
import com.example.coursereview.service.ReplyService;
import com.example.coursereview.service.CommentService;
import lombok.RequiredArgsConstructor; 

@RestController
@RequestMapping({"/replies"})
@RequiredArgsConstructor
public class ReplyController {

   private final ReplyService replyService;
   private final CommentService commentService;

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
