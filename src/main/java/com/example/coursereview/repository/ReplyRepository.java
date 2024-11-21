package com.example.coursereview.repository;

import com.example.coursereview.model.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepltRepository extends JpaRepository<Reply, Integer> {
    List<Reply> findByParentCommentId(int commentId);
}
