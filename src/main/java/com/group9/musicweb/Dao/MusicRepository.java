package com.group9.musicweb.Dao;

import com.group9.musicweb.entity.Music;
import com.group9.musicweb.entity.Userlog;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MusicRepository extends JpaRepository<Music, Integer> {


    @Query(value = "select * from music where name like CONCAT('%',?1,'%') and ischeckd = 1", nativeQuery = true)
    List<Music> searchLikeMusic(String key);


    List<Music> findAllByZuozhe(String zuozhe);

    Music findMusicById(int id);

    @Query(value = "select * from music where ischeckd = 1", nativeQuery = true)
    List<Music> getALLcheckedmusic();

    Music findById(int id);
}
