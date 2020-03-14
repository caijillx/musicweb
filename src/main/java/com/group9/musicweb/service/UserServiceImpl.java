package com.group9.musicweb.service;

import com.group9.musicweb.Dao.UserRepository;
import com.group9.musicweb.entity.Comment;
import com.group9.musicweb.entity.User;
import com.group9.musicweb.entity.Userlog;
import org.hibernate.persister.entity.NamedQueryLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.thymeleaf.model.IComment;

import javax.annotation.Resource;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Resource
    private JdbcTemplate jdbcTemplate;


    @Override
    public User checkUser(String username, String password) {
        User user = userRepository.findByNameAndPwd(username, password);
        return user;
    }

    @Override
    public boolean isFindUserByUsername(String username) {
        User user = userRepository.findByName(username);
        return user == null;
    }

    @Override
    public boolean isFindUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return user == null;
    }

    @Override
    public boolean isFindUserByPhone(String phone) {
        User user = userRepository.findByPhone(phone);
        return user == null;
    }

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void updatePwd(User user, String newpwd) {
        user.setPwd(newpwd);
        userRepository.save(user);
    }

    @Override
    public User getUser(String username) {
        User user = userRepository.findByName(username);
        return user;
    }

    @Override
    public boolean isRightPwd(User user, String password) {
        return user.getPwd().equals(password);
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public List<Comment> queryComment(int userid) {
        String sql = "select * from comment where user_id = " + userid + " order by creat_time desc";
        System.out.println(sql);
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Comment>(Comment.class));
    }

    @Override
    public User findUserById(int userid) {
        return userRepository.findById(userid);
    }

}
