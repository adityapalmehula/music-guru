package com.comment;

public class Comment {

	private String message;
	private String reply;
	
	public Comment() {
    	super();
    }
	public Comment(String message,String reply)
	{
		this.message=message;
		this.reply=reply;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getReply() {
		return reply;
	}
	public void setReply(String reply) {
		this.reply = reply;
	}
	
}
