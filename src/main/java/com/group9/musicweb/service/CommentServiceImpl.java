package com.group9.musicweb.service;

import com.group9.musicweb.Dao.CommentRepository;
import com.group9.musicweb.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public void saveComment(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public List<Comment> findCommentsByMusic_Id(int id) {
        return commentRepository.findCommentsByMusic_Id(id);
    }
}
