
package com.example.coursereview.controller;

import com.example.coursereview.service.CommentService;
import com.example.coursereview.model.UserComment;
import com.example.coursereview.utils.ProhibitedWordsFilter;

public class UserController {
    private CommentService commentService = new CommentService();

    // Method to delete a comment by its ID
    public void deleteComment(int commentId) {
        boolean deleted = commentService.deleteComment(commentId);
        if (deleted) {
            System.out.println("Comment deleted successfully.");
        } else {
            System.out.println("Comment not found.");
        }
    }

    // Method to submit a comment after checking for prohibited words
    public void submitComment(String commentText) {
        if (ProhibitedWordsFilter.containsProhibitedWords(commentText)) {
            System.out.println("Comment contains prohibited words and cannot be posted.");
        } else {
            UserComment comment = new UserComment(0, commentText); // Assuming a constructor for new comments
            commentService.addComment(comment); // Save to database or list
            System.out.println("Comment posted successfully.");
        }
    }
}
