package ftn.xmlws.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ftn.xmlws.domain.User;
import ftn.xmlws.service.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {
	
	@Autowired
	private UserService userService;

	@RequestMapping(value = "getUsers", method = RequestMethod.GET )
	public ResponseEntity<List<User>> getUserAds() {
		List<User> users = userService.findAll();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<User> addUserAd(@RequestBody User user) {
		User newUser = userService.save(user);
		System.out.println("TU SAM");
		return new ResponseEntity<>(newUser, HttpStatus.OK);

	}
	
}
