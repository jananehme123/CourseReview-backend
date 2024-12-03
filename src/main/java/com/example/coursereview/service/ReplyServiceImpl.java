package com.example.coursereview.service;

import com.example.coursereview.controller.ResourceNotFoundException;
import com.example.coursereview.model.Comment;
import com.example.coursereview.model.Reply;
import com.example.coursereview.model.User;
import com.example.coursereview.repository.CommentRepository;
import com.example.coursereview.repository.ReplyRepository;
import com.example.coursereview.repository.UserRepository;
import com.example.coursereview.utils.ProhibitedWordsFilter;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;



@Service
public class ReplyServiceImpl implements ReplyService{

    private final ReplyRepository replyRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public ReplyServiceImpl(ReplyRepository replyRepository, CommentRepository commentRepository, UserRepository userRepository) {
        this.replyRepository = replyRepository;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Reply addReplyToComment(int commentId, Reply reply) throws ResourceNotFoundException {
        if (ProhibitedWordsFilter.containsProhibitedWords(reply.getText())) {
            throw new IllegalStateException("Comment contains prohibited words");
        }
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found with id " + commentId));

        if (reply.getUser() == null || reply.getUser().getId() == 0) {
            throw new IllegalStateException("User ID is required");
        }
        Optional<User> user = userRepository.findById(reply.getUser().getId());
        if (user.isEmpty()) {
            throw new ResourceNotFoundException("User not found with id " + reply.getUser().getId());
        }
        reply.setParentComment(comment);
        reply.setUser(user.get());
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
