package com.songlist;

import java.io.Serializable;


import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Song {
	  
	 @Id
	 private String id;
	 
	 private String songname;
	 private Integer year;
	 private String singername;
	 private String musicdirector;
	 private String language;
	 private Integer rating;
	 private Integer duration;
	 private String type;
	 private String song_path;
	 private String image_path;
	 
    public Song()
    {
    }
   
    public Song(String id ,String songname, Integer year, String singername, String musicdirector, String language,
			Integer rating, Integer duration, String type, String song_path, String image_path) {
		super();
		this.id = id;
		this.songname = songname;
		this.year = year;
		this.singername = singername;
		this.musicdirector = musicdirector;
		this.language = language;
		this.rating = rating;
		this.duration = duration;
		this.type = type;
		this.song_path = song_path;
		this.image_path = image_path;
	}
    
    
	

	public String getId() {
		return id;
	}

//	public void setId(String id) {
//		this.id = id;
//	}

	public String getSongname() {
		return songname;
	}

	public void setSongname(String songname) {
		this.songname = songname;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getSingername() {
		return singername;
	}

	public void setSingername(String singername) {
		this.singername = singername;
	}

	public String getMusicdirector() {
		return musicdirector;
	}

	public void setMusicdirector(String musicdirector) {
		this.musicdirector = musicdirector;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSong_path() {
		return song_path;
	}

	public void setSong_path(String song_path) {
		this.song_path = song_path;
	}

	public String getImage_path() {
		return image_path;
	}

	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}
  //------------------
//	@Override
//    public String toString() {
//        return String.format(id);
//    }

	
}
