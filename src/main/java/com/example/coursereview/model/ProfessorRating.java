package com.example.coursereview.model;

public class ProfessorRating {
    private int id;
    private int professorId;
    private int rating; // rating value, e.g., 1-5 stars
    private String review;

    public ProfessorRating(int id, int professorId, int rating, String review) {
        this.id = id;
        this.professorId = professorId;
        this.rating = rating;
        this.review = review;
    }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getProfessorId() { return professorId; }
    public void setProfessorId(int professorId) { this.professorId = professorId; }

    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }

    public String getReview() { return review; }
    public void setReview(String review) { this.review = review; }
}
