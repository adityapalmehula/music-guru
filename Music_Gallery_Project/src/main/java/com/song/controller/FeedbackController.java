package com.song.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.feedback.Feedback;
import com.song.repository.Feedbackrepository;



@RestController
@RequestMapping("/feedbacklog")
public class FeedbackController {
  
	@Autowired
	private Feedbackrepository feedbackrepository;

    public FeedbackController(Feedbackrepository feedbackrepository) {
        this.feedbackrepository = feedbackrepository;
    }
//    to store user feedback
    @RequestMapping(value = "/Userfeedback", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> create(@RequestBody Feedback feedback) {
		 try {
			 feedbackrepository.save(feedback);
		  return ResponseEntity.noContent().build();
		 } catch (Exception e) {
		  return ResponseEntity.status(HttpStatus.CONFLICT).build();
		 }
		}
}
