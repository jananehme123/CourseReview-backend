package com.example.coursereview.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ProfessorRating")
public class ProfessorRating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int rating; // rating value, e.g., 1-5 stars

    @ManyToOne
    @JoinColumn(name = "professor_id", insertable = false)
    private Professor professor;

    public ProfessorRating(int id, int professorId, int rating) {
        this.id = id;
        this.rating = rating;
    }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Professor getProfessor() { return professor; }
    public void setProfessor(Professor professor) { this.professor = professor; }

    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }


}
