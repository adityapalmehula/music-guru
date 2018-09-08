package com.song.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.comment.Comment;



public interface Commentrepository extends MongoRepository<Comment, String>{

}
