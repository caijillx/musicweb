package com.group9.musicweb.service;

import com.group9.musicweb.entity.Comment;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CommentService {
    void saveComment(Comment comment);

    List<Comment> findCommentsByMusic_Id(int id);
}
