package com.group9.musicweb.Dao;

import com.group9.musicweb.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

    User findByNameAndPwd(String name, String pwd);

    User findByName(String name);

    User findByEmail(String email);

    User findByPhone(String phone);

}
