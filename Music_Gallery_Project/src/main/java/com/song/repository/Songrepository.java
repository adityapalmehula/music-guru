package com.song.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.register.Register;
import com.songlist.Song;

public interface Songrepository extends MongoRepository<Song, String> {
	//public Register findSongByName(String songname);
	public List<Song> findBysongnameLike(String name);

}
