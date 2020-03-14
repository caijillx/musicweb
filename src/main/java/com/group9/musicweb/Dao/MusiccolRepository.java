package com.group9.musicweb.Dao;

import com.group9.musicweb.entity.Musiccol;
import com.group9.musicweb.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MusiccolRepository extends JpaRepository<Musiccol, Integer> {

    List<Musiccol> findAllByUser(User user);

    @Query(value = "select * from musiccol where user_id=?1 and music_id=?2", nativeQuery = true)
    Musiccol findMusiccol(int uid, int mid);
}
