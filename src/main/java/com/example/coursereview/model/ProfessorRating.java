package com.example.coursereview.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "ProfessorRating")
public class ProfessorRating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int rating; // rating value, e.g., 1-5 stars
    private int userId;

    @ManyToOne
    @JoinColumn(name = "professor_id", nullable = false)
    @JsonIgnoreProperties("ratings")
    private Professor professor;

    public ProfessorRating() { }
    public ProfessorRating(int id, Professor professor, int rating, int userId) {
        this.id = id;
        this.professor = professor;
        this.rating = rating;
        this.userId = userId;
    }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Professor getProfessor() { return professor; }
    public void setProfessor(Professor professor) { this.professor = professor; }

    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

}
