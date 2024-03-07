package com.example.validatesonginformation.repository;

import com.example.validatesonginformation.model.Song;
import org.springframework.data.repository.CrudRepository;

public interface IRepositorySong extends CrudRepository<Song,Long> {
}
