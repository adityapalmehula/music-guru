package com.song.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.comment.Comment;
import com.contact.Contact;


public interface Contactrepository extends MongoRepository<Contact, String>{
	
}
