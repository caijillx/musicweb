package com.group9.musicweb.service;

import com.group9.musicweb.Dao.MusicRepository;
import com.group9.musicweb.entity.Music;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicServiceImpl implements MusicService {
    @Autowired
    private MusicRepository musicRepository;

    @Override
    public void saveMusic(Music music) {
        musicRepository.save(music);
    }

    @Override
    public List<Music> searchMusic(String key) {
        List<Music> l1 = musicRepository.searchLikeMusic(key);
        List<Music> l2 = musicRepository.findAllByZuozhe(key);
        l1.remove(l2);
        l1.addAll(l2);
        return l1;
    }

    @Override
    public Music findMusicById(int id) {
        return musicRepository.findMusicById(id);
    }

    @Override
    public List<Music> getallcheckedmusic() {
        return musicRepository.getALLcheckedmusic();
    }

}
