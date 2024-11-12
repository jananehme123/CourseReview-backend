package com.example.coursereview.service;

import com.example.coursereview.model.UserComment;
import java.util.List;

public class CommentService {
    private List<UserComment> comments; // Assuming a list holds user comments

    public boolean deleteComment(int commentId) {
        return comments.removeIf(comment -> comment.getId() == commentId);
    }

    // Optional: Add a method to add comments if you plan to use it in submitComment
    public void addComment(UserComment comment) {
        comments.add(comment);
    }
}
