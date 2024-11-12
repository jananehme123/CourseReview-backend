package com.example.coursereview.controller;

import com.example.coursereview.model.Comment;
import java.util.List;
import java.util.Optional;

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
@RequestMapping({"/comments"})
public class CommentController {
   @Autowired
   private CommentService commentService;

   public CommentController() {
   }

   @GetMapping
   public List<Comment> getAllComments() {
      return this.commentService.getAllComments();
   }

   @GetMapping({"/{id}"})
   public Optional<Comment> getCommentById(@PathVariable int id) {
      return this.commentService.getCommentById(id);
   }

   @PostMapping
   public Comment addComment(@RequestBody Comment comment) {
      return this.commentService.saveComment(comment);
   }

   @PutMapping({"/{id}"})
   public Comment updateComment(@PathVariable int id, @RequestBody Comment comment) {
      comment.setText(comment.getText());
      return this.commentService.saveComment(comment);
   }

   @DeleteMapping({"/{id}"})
   public void deleteComment(@PathVariable int id) {
      this.commentService.deleteComment(id);
   }
}
