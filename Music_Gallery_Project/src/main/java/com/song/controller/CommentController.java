package com.song.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.comment.Comment;
import com.song.repository.Commentrepository;



@RestController
@RequestMapping("/commentlog")
public class CommentController {
     
	@Autowired
	private Commentrepository commentrepository;

    public CommentController(Commentrepository commentrepository) {
        this.commentrepository = commentrepository;
    }
//   to store User comment 
    @RequestMapping(value = "/Usercomment", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> create(@RequestBody Comment comment) {
		 try {
			 commentrepository.save(comment);
		  return ResponseEntity.noContent().build();
		 } catch (Exception e) {
		  return ResponseEntity.status(HttpStatus.CONFLICT).build();
		 }
		}
}
