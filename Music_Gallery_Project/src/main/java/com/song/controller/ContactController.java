package com.song.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.contact.Contact;
import com.song.repository.Contactrepository;



@RestController
@RequestMapping("/contactlog")
public class ContactController {
	
	@Autowired
	private Contactrepository contactrepository;

    public ContactController(Contactrepository contactrepository) {
        this.contactrepository = contactrepository;
    }
//  to store user contact details
    @RequestMapping(value = "/Usercontact", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> create(@RequestBody Contact contact) {
		 try {
			 contactrepository.save(contact);
		  return ResponseEntity.noContent().build();
		 } catch (Exception e) {
		  return ResponseEntity.status(HttpStatus.CONFLICT).build();
		 }
		}

}
