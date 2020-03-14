package com.group9.musicweb.Dao;


import com.group9.musicweb.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    //根据音乐id查找其所有的评论
    List<Comment> findCommentsByMusic_Id(int id);
}
