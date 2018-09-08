package com.song.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.feedback.Feedback;

public interface Feedbackrepository extends MongoRepository<Feedback, String>{

}
