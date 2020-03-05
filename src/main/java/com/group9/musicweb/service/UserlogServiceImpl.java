package com.group9.musicweb.service;

import com.group9.musicweb.Dao.UserlogRepository;
import com.group9.musicweb.entity.User;
import com.group9.musicweb.entity.Userlog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserlogServiceImpl implements UserlogService {
    @Autowired
    private UserlogRepository userlogRepository;

    @Override
    public List<Userlog> queryUserlog(int userid) {
        return userlogRepository.QueryUserlog(userid);
    }

    @Override
    public void saveUserlog(Userlog userlog) {
        userlogRepository.save(userlog);
    }
}
