package com.example.coursereview.model;

import jakarta.persistence.*;

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
   private User user;

   // Many-to-One relationship with Professor
   @ManyToOne
   @JoinColumn(name = "professor_id", nullable = false) // foreign key 
   private Professor professor;

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

}
