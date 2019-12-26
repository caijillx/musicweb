package com.group9.musicweb.service;

import com.group9.musicweb.entity.User;

public interface UserService {
    User checkUser(String username, String password);
    boolean isFindUserByUsername(String username);
    boolean isFindUserByEmail(String email);
    boolean isFindUserByPhone(String phone);

}
