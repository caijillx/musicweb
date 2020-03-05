package com.group9.musicweb.Dao;

import com.group9.musicweb.entity.User;
import com.group9.musicweb.entity.Userlog;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserlogRepository extends JpaRepository<Userlog, Integer> {
    @Query(value = "select * from userlog where user_id = ?1 order by add_time desc", nativeQuery = true)
    List<Userlog> QueryUserlog(int userid);
}