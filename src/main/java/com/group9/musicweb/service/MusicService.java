package com.group9.musicweb.service;

import com.group9.musicweb.entity.Music;

import java.util.List;

public interface MusicService {
    void saveMusic(Music music);

    List<Music> searchMusic(String key);

    Music findMusicById(int id);

    List<Music> getallcheckedmusic();

}
