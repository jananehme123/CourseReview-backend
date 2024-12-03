package com.example.coursereview.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

@Entity
@Table(
   name = "Reply"
)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Reply {
   @Id
   @GeneratedValue(
      strategy = GenerationType.IDENTITY
   )
   private int id;
   private String text;

   // Many-to-One relationship with Comment (parent Comment)
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "comment_id", nullable = false) // foreign key
   @JsonBackReference
   private Comment parentComment;

   // Many-to-One relationship with User
   @ManyToOne
   @JoinColumn(name = "user_id", nullable = false) // foreign key 
   @JsonIgnoreProperties("replies")
   private User user;

   public Reply() {
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getText() {
      return text;
   }

   public void setText(String text) {
      this.text = text;
   }

    public Comment getParentComment() {
        return parentComment;
    }

    public void setParentComment(Comment parentComment) {
        this.parentComment = parentComment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
