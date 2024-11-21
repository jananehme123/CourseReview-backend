package com.example.coursereview.service;

import com.example.coursereview.model.Reply;
import com.example.coursereview.repository.ReplyRepository;
import com.example.coursereview.utils.ProhibitedWordsFilter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;



@Service
public class ReplyServiceImpl implements ReplyService{

    private final ReplyRepository replyRepository;

    public ReplyServiceImpl(ReplyRepository replyRepository) {
        this.replyRepository = replyRepository;
    }

    @Override
    public List<Reply> getAllReplies() {
        return replyRepository.findAll();
    }

    @Override
    public List<Reply> getRepliesByCommentId(int commentId) {
        return replyRepository.findByParentCommentId(commentId);
    }

    @Override
    public Optional<Reply> getReplyById(int id) {
        return replyRepository.findById(id);
    }

    @Override
    public Reply saveReply(Reply reply) {
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
