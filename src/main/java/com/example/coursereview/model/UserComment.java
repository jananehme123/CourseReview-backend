package com.example.coursereview.model;

public class UserComment {
    private int id;
    private String commentText;

    // Constructor
    public UserComment(int id, String commentText) {
        this.id = id;
        this.commentText = commentText;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    // Optional: Override toString() for better readability if needed
    @Override
    public String toString() {
        return "UserComment{" +
                "id=" + id +
                ", commentText='" + commentText + '\'' +
                '}';
    }
}
