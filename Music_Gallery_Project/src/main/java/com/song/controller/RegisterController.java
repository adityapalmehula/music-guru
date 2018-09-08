package com.song.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.Query;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jayway.jsonpath.Criteria;
import com.register.Register;
import com.song.configue.MongoDBConfigure;
import com.song.repository.Registerrepository;
import com.song.security.JwtAuthenticationProvider;
import com.song.security.JwtGenerator;


@RestController
@RequestMapping("/userlog")
public class RegisterController {

	private JwtGenerator jwtGenerator;
	private Registerrepository registerrepository;

	public RegisterController(JwtGenerator jwtGenerator,Registerrepository registerrepository) {
		this.jwtGenerator = jwtGenerator;
		this.registerrepository = registerrepository;
	}
	
// to save data of user registration
//	@RequestMapping(value = "/Userdata", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<Void> create(@Valid @RequestBody Register register , BindingResult bindingResult) 
//	{
//		if (bindingResult.hasErrors()) {
//			System.out.println("somethis is wrong");
//			bindingResult
//			.getFieldErrors()
//			.stream()
//			.forEach(f -> System.out.println(f.getField() + ": " + f.getDefaultMessage()));
//		}else {
//			registerrepository.save(register);
//			//response.put("ok", "Registered Succesfuly");
//			return ResponseEntity.noContent().build();
//		}
//		return ResponseEntity.accepted().build();
//	}
	
//// admin and user varification 
//	@RequestMapping(value = "/verify", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<Map<String, String>> login(@RequestBody Register register) {
//		Map<String, String> response = new HashMap<String, String>();
//		String email = String.valueOf(register.getEmail());
//		String password = String.valueOf(register.getPassword());
//		// String type = String.valueOf(register.getType());
//
//if ((registerrepository.findOneByEmail(email) != null)
//				&& (registerrepository.findOneByPassword(password) != null)) {
//			String test=String.valueOf(registerrepository.findRegisterByEmail(register.getEmail()));
//			response.put("ok",test );
//			System.out.println(registerrepository.findRegisterByEmail(register.getEmail()));
//			return ResponseEntity.accepted().body(response);
//
//		} else {
//			if ((registerrepository.findOneByEmail(email) != null)) {
//				response.put("error", " !Opss     -WRONG PASSWORD");
//				return ResponseEntity.badRequest().body(response);
//			} else if ((registerrepository.findOneByPassword(password) != null)) {
//				response.put("error", " !Opss    -WRONG EMAIL");
//				return ResponseEntity.badRequest().body(response);
//			} else {
//				response.put("error", " !Opss    -Both Email AND Password Wrong");
//				return ResponseEntity.badRequest().body(response);
//			}
//		}
//	}
	
	
	@RequestMapping(value = "/role", method = RequestMethod.GET)
	public ResponseEntity<Map<String, String>> add() {
		Map<String, String> response = new HashMap<String, String>();
		String s= JwtAuthenticationProvider.type;
			response.put("type",s);
			return ResponseEntity.accepted().body(response);
		}
	
	
	
	
	
	
	@PostMapping
	public ResponseEntity<Map<String, String>> generate(@RequestBody Register register) {
		Map<String, String> response = new HashMap<String, String>();
		String email = String.valueOf(register.getEmailId());
		if (registerrepository.findOneByEmailId(email) == null) {
			response.put("error", "Please enter valid emailId email");
			return ResponseEntity.badRequest().body(response);
		}
		Register authenticateUser = registerrepository.findOneByEmailId(email);

		if (!authenticateUser.getPassword().contentEquals(register.getPassword())) {
			response.put("error", "Please enter valid password pasw");
			return ResponseEntity.badRequest().body(response);
		}
		if (authenticateUser != null && (authenticateUser.getPassword().contentEquals(register.getPassword()))) {
			register.setRole(authenticateUser.getRole());
			String token = jwtGenerator.generate(register);
			response.put("token", token);
			return ResponseEntity.ok().body(response);
		} else {
			return ResponseEntity.badRequest().build();
		}
	}

}
