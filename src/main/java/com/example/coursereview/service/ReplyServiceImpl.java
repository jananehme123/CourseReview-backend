package com.example.coursereview.service;

import com.example.coursereview.controller.ResourceNotFoundException;
import com.example.coursereview.model.Comment;
import com.example.coursereview.model.Reply;
import com.example.coursereview.repository.CommentRepository;
import com.example.coursereview.repository.ReplyRepository;
import com.example.coursereview.utils.ProhibitedWordsFilter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;



@Service
public class ReplyServiceImpl implements ReplyService{

    private final ReplyRepository replyRepository;
    private final CommentRepository commentRepository;

    public ReplyServiceImpl(ReplyRepository replyRepository, CommentRepository commentRepository) {
        this.replyRepository = replyRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public Reply addReplyToComment(int commentId, Reply reply) throws ResourceNotFoundException {
       // return replyRepository.addById(commentId, reply);
        Optional<Comment> comment = commentRepository.findById(commentId);
        if (!comment.isPresent()) {
            throw new ResourceNotFoundException("Comment not found with id " + commentId);
        }
        reply.setParentComment(comment.get());
        return replyRepository.save(reply);
    }

    @Override
    public List<Reply> getRepliesByCommentId(int commentId) {
        return replyRepository.findByParentCommentId(commentId);
    }

    @Override
    public List<Reply> getAllReplies() {
        return replyRepository.findAll();
    }

    @Override
    public Optional<Reply> getReplyById(int id) {
        return replyRepository.findById(id);
    }

    @Override
    public Reply updateReply(Reply reply) throws ResourceNotFoundException {
        if (ProhibitedWordsFilter.containsProhibitedWords(reply.getText())) {
            throw new IllegalStateException("Reply contains prohibited words");
        }
        return replyRepository.save(reply);
    }

    @Override
    public void deleteReply(int id) {
        replyRepository.deleteById(id);
    }

}
