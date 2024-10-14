package com.example.coursereview.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;  // Change from Long to int

    private String name;
    private String faculty;

    // Define the one-to-many relationship with Course
    @OneToMany(mappedBy = "department")
    private List<Course> courses;

    // Define the one-to-many relationship with Professor
    @OneToMany(mappedBy = "department")
    private List<Professor> professors;

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public List<Professor> getProfessors() {
        return professors;
    }

    public void setProfessors(List<Professor> professors) {
        this.professors = professors;
    }
}
