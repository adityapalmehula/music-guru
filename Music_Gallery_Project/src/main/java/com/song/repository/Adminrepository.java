package com.song.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.admin.Admin;

public interface Adminrepository extends MongoRepository<Admin, String>{
	public Admin findByEmail(String email);
    public Admin findByPassword(String password);

}
