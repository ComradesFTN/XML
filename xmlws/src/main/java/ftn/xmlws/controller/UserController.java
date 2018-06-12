package ftn.xmlws.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import ftn.xmlws.domain.User;
import ftn.xmlws.domain.VerificationToken;
import ftn.xmlws.service.EmailService;
import ftn.xmlws.service.UserService;

@Controller
@RequestMapping(value = "/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private EmailService emailService;

	@RequestMapping(value = "getUsers", method = RequestMethod.GET )
	public ResponseEntity<List<User>> getUserAds() {
		List<User> users = userService.findAll();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<User> addUserAd(@RequestBody User user) {
		User newUser = userService.save(user);
		
		if(user.getUserType()==2) {
			try {
				emailService.send(user);
			} catch (Exception e) {
				System.out.println("Greska prilikom slanja emaila: " + e.getMessage());
			}
		}
		System.out.println("NADJI ME!!!!!!! KREIRAO SAM KORISNIKA!!!");
		return new ResponseEntity<>(newUser, HttpStatus.OK);

	}
	
	@RequestMapping(value = "/registrationConfirm", method = RequestMethod.GET)
	public RedirectView confirmRegistration(@RequestParam("token") String token) {

		VerificationToken verificationToken = userService.getVerificationToken(token);
		if (verificationToken == null) {
			System.out.println("Nema tokena");
			return new RedirectView("NeregistrovaniKorisnici/badUser.html");
		}

		User user = verificationToken.getUser();

		user.setConfirmed(true);
		userService.save(user);
		return new RedirectView("http://localhost:8080/xmlws/index.html");

	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteUser(@PathVariable Long id) {
		User deleted = userService.delete(id);
		return new ResponseEntity<>(deleted, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}",method = RequestMethod.PUT)
	public ResponseEntity<User> editBaned(@PathVariable Long id) {
		User user = userService.findOne(id);
		if(user.isBaned()==false) {
			user.setBaned(true);
		}else {
			user.setBaned(false);
		}
		User editedUser = userService.save(user);
		return new ResponseEntity<>(editedUser, HttpStatus.OK);
	}
	
}
