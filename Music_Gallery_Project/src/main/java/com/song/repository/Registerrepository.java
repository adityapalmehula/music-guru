package com.song.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.register.Register;

public interface Registerrepository extends MongoRepository<Register, String> {
//   public Register findOneByEmail(String email);
//    public Register findOneByPassword(String password);
//    public Register findByEmail(String email);
//    public Register findRegisterByEmail(String email);
//   
    public Register findOneByEmailId(String email);

	public Register findOneByPassword(String password);

	public Register findUserByEmailId();
}
