package com.example.coursereview.service;

import com.example.coursereview.controller.ResourceNotFoundException;
import com.example.coursereview.model.Reply;

import java.util.List;
import java.util.Optional;

public interface ReplyService {
    Reply addReplyToComment(int commentId, Reply reply) throws ResourceNotFoundException;
    List<Reply> getRepliesByCommentId (int commentId);
    List<Reply> getAllReplies();
    Optional<Reply> getReplyById(int Id);
    Reply updateReply(Reply reply) throws ResourceNotFoundException;
    void deleteReply(int id);
}