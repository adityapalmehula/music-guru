package com.song.controller;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.register.Register;
import com.song.repository.Registerrepository;
import com.song.security.JwtGenerator;

@RestController
@RequestMapping("/submitdata")
public class submit {
   
	private Registerrepository registerrepository;

	public submit(Registerrepository registerrepository) {
		
		this.registerrepository = registerrepository;
	}
	
// to save data of user registration
	@RequestMapping(value = "/nowuser", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> create(@Valid @RequestBody Register register , BindingResult bindingResult) 
	{
		if (bindingResult.hasErrors()) {
			System.out.println("somethis is wrong");
			bindingResult
			.getFieldErrors()
			.stream()
			.forEach(f -> System.out.println(f.getField() + ": " + f.getDefaultMessage()));
		}else {
			registerrepository.save(register);
			//response.put("ok", "Registered Succesfuly");
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.accepted().build();
	}
	
}
