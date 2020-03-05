package com.group9.musicweb.service;

import com.group9.musicweb.entity.Comment;
import com.group9.musicweb.entity.User;
import com.group9.musicweb.entity.Userlog;

import java.util.List;

public interface UserService {
    User checkUser(String username, String password);
    boolean isFindUserByUsername(String username);
    boolean isFindUserByEmail(String email);
    boolean isFindUserByPhone(String phone);
    void addUser(User user);
    User getUser(String username);
    boolean isRightPwd(User user,String password);
    void updatePwd(User user,String password);
    void saveUser(User user);
    List<Comment> queryComment(int userid);
}

