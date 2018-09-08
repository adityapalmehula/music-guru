package com.song.configue;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.song.controller.CommentController;
import com.song.repository.Adminrepository;
import com.song.repository.Contactrepository;
import com.song.repository.Feedbackrepository;
import com.song.repository.Mymusicrepository;
import com.song.repository.Registerrepository;
import com.song.repository.Songrepository;

 

@EnableMongoRepositories(basePackageClasses = {Songrepository.class,Registerrepository.class,Adminrepository.class})
@Configuration
public class MongoDBConfigure {
	@Bean
    CommandLineRunner commandLineRunner(Songrepository songrepository) {
        return null;
    }
	CommandLineRunner commandLineRunner(Registerrepository registerrepository) {
        return null;
    }
	CommandLineRunner commandLineRunner(Adminrepository adminrepository) {
        return null;
    }
	CommandLineRunner commandLineRunner(Contactrepository contactrepository) {
        return null;
    }
	CommandLineRunner commandLineRunner(Feedbackrepository feedbackrepository) {
        return null;
    }
	CommandLineRunner commandLineRunner(CommentController commentController) {
        return null;
    }
	CommandLineRunner commandLineRunner(Mymusicrepository mymusicrepository) {
        return null;
    }
	
}
