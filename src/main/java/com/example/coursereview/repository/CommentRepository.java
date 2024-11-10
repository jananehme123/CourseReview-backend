// Source code is decompiled from a .class file using FernFlower decompiler.
package com.example.coursereview.repository;

import com.example.coursereview.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
