package com.example.validatesonginformation.service;

import com.example.validatesonginformation.model.Song;
import com.example.validatesonginformation.repository.IRepositorySong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class SongService implements ISongService{
    @Autowired
    private IRepositorySong iRepositorySong;
    @Override
    public Iterable<Song> findAll() {
        return iRepositorySong.findAll();
    }

    @Override
    public void save(Song song) {
        iRepositorySong.save(song);
    }

    @Override
    public Optional<Song> findById(Long id) {
        return iRepositorySong.findById(id);
    }
}
