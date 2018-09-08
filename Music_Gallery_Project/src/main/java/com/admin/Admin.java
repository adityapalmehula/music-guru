package com.admin;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Admin implements Serializable{
	private String email;
	private String password;
	
	 public Admin() {
	    	super();
	    }
	 
	 public Admin(String email , String password) {
	        this.email = email;
	        this.password = password; 
	    }
	 
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
