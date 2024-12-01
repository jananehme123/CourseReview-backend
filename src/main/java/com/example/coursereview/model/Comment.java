package com.example.coursereview.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(
   name = "Comment"
)
public class Comment {
   @Id
   @GeneratedValue(
      strategy = GenerationType.IDENTITY
   )
   private int id;
   private String text;

   // Many-to-One relationship with User
   @ManyToOne
   @JoinColumn(name = "user_id", nullable = false) // foreign key
   @JsonIgnoreProperties("comments")
   private User user;

   // Many-to-One relationship with Professor
   @ManyToOne
   @JoinColumn(name = "professor_id", nullable = false) // foreign key 
   @JsonIgnoreProperties("comments")
   private Professor professor;

  // One-to-Many relationship with Reply
  @OneToMany(mappedBy = "parentComment", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Reply> replies;

   public Comment() {
   }

   public int getId() {
      return this.id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getText() {
      return this.text;
   }

   public void setText(String text) {
      this.text = text;
   }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Professor getProfessor() {
        return this.professor;
    }

    public void setProfessor(Professor professor) {
      this.professor = professor;
    }

    public List<Reply> getReplies() {
      return replies;
    }

    public void setReplies(List<Reply> replies) {
       this.replies = replies;
    }
}
