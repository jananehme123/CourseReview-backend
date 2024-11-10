// Source code is decompiled from a .class file using FernFlower decompiler.
package com.example.coursereview.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
//import jakarta.persistence.ManyToOne;

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
   private String anonymousId;
   // @ManyToOne
   //private User user;  //relationship with user entity

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

   public String getAnonymousId() {
    return anonymousId;
    }

    public void setAnonymousId(String anonymousId) {
     this.anonymousId = anonymousId;
    }

//    public User getUser(){
//     return user;
//    }

//    public void setUser(User user){
//     this.user=user;
//    }

   //if we want the user to have a username, from user class
   //public String getUsername() {
   //return user != null ? user.getUsername() : null;
   //}
}
