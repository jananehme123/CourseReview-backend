package com.example.coursereview.service;

import com.example.coursereview.model.Reply;

import java.util.List;
import java.util.Optional;

public interface ReplyService {
    List<Reply> getAllReplies();
    List<Reply> getRepliesByCommentId (int commentId);
    Optional<Reply> getReplyById(int Id);
    Reply saveReply(Reply reply);
    void deleteReply(int id);
}
