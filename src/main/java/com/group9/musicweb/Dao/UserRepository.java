package com.group9.musicweb.Dao;

import com.group9.musicweb.entity.Comment;
import com.group9.musicweb.entity.User;
import com.group9.musicweb.entity.Userlog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {

    User findByNameAndPwd(String name, String pwd);

    User findByName(String name);

    User findByEmail(String email);

    User findByPhone(String phone);

}
