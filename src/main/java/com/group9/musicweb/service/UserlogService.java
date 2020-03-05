package com.group9.musicweb.service;

import com.group9.musicweb.entity.User;
import com.group9.musicweb.entity.Userlog;

import java.util.List;

public interface UserlogService {
    void saveUserlog(Userlog userlog);

    //根据用户查询其所有登录日志
    List<Userlog> queryUserlog(int userid);

}
