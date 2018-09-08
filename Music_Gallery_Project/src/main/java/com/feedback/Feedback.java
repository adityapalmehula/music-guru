package com.feedback;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Feedback { 
	
	public String rating;
	public String message;
	
	public Feedback() {
    	super();
    }
	
	public Feedback(String rating, String message)
	{
		this.rating = rating;
		this.message =message;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
     
}
