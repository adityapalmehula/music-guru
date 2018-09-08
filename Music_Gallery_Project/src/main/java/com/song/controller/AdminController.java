package com.song.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.admin.Admin;
import com.register.Register;
import com.song.repository.Adminrepository;



@RestController
@RequestMapping("/adminlog")
public class AdminController {
	
	@Autowired
	private Adminrepository adminrepository;
	
	public AdminController(Adminrepository adminrepository) {
        this.adminrepository = adminrepository;
    }
//	to varfy admin login data 
	 @RequestMapping(value = "/verify", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<Map<String, String>> login(@RequestBody Admin admin) {
	    Map<String, String> response = new HashMap<String, String>();
	    String email = String.valueOf(admin.getEmail());
	    String password = String.valueOf(admin.getPassword());

	  if ((adminrepository.findByEmail(email) != null) && (adminrepository.findByPassword(password) != null))
	    {
	    response.put("ok", "Logedin Succesfuly");
	    return ResponseEntity.accepted().body(response);
	   
	    } else 
	    {
	    	if ((adminrepository.findByEmail(email) != null))
	    	{
	    response.put("error", " !Opss     -WRONG PASSWORD");
	    return ResponseEntity.badRequest().body(response);
	    	}
	    	else if((adminrepository.findByPassword(password) != null))
	    	{response.put("error", " !Opss    -WRONG EMAIL");
	        return ResponseEntity.badRequest().body(response);
	    	}else
	    	{
	    		response.put("error", " !Opss    -Both Email AND Password Wrong");
	            return ResponseEntity.badRequest().body(response);
	    	}
	  }
	 }

}
