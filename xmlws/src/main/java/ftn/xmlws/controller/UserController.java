package ftn.xmlws.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import ftn.xmlws.domain.User;
import ftn.xmlws.domain.VerificationToken;
import ftn.xmlws.dto.UserDTO;
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
		System.out.println("OVDE SAM!!!!NADJI ME!!!");
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
	@RequestMapping(value = "getUser", method = RequestMethod.GET )
	public ResponseEntity<User> getUser(@RequestHeader("Cookie") String cookie) {
		System.out.println(cookie);
		String[] parse = cookie.split("\\=");
		String[] parse2 = parse[1].split("\\;");
		User user = userService.findOne(Long.parseLong(parse2[0]));
		return new ResponseEntity<>(user, HttpStatus.OK);
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
	public ResponseEntity<User> editbanned(@PathVariable Long id) {
		User user = userService.findOne(id);
		if(user.isbanned()==false) {
			user.setbanned(true);
		}else {
			user.setbanned(false);
		}
		User editedUser = userService.save(user);
		return new ResponseEntity<>(editedUser, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json")	 
	public ResponseEntity<String> loginProcess(@RequestBody UserDTO userDTO) {
		String email = userDTO.getEmail();
		String pass = userDTO.getPassword();
		System.out.println("email: " + email + "pass: "+ pass);		
		User currentUser = userService.getUserByEmail(email);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Acccess-Control-Expose-Headers","Error2");
		if(currentUser.equals(null)) {
			headers.add("Error2","Nepostojeci email!");
			return new ResponseEntity<>(headers,HttpStatus.NOT_FOUND);
			
		}
		if(currentUser.getPassword().equals(pass)) {
			if(currentUser.isConfirmed()) {
				if(currentUser.getUserType()==1 || currentUser.getUserType()==2) {
					headers.add(HttpHeaders.SET_COOKIE, "Id="+currentUser.getId());
					return new ResponseEntity<>(headers,HttpStatus.OK);				
				}
			} else {
				headers.add("Error2","Vas nalog nije aktivan!");
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
				}
			
		}
		headers.add("Error2","Username i/ili password su netacni.");
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);	
	}
	
	@RequestMapping(value = "/editUser/{userId}",method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<User> editUser(@PathVariable Long userId, @RequestBody User user) {
		User editedUser = userService.findOne(userId);
		editedUser.setName(user.getName());
		editedUser.setLastName(user.getLastName());
		editedUser.setUserName(user.getUserName());
		editedUser.setPassword(user.getPassword());
		User editedUser2 = userService.save(editedUser);
		return new ResponseEntity<>(editedUser2, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET )
	public ResponseEntity<User> getUser() {
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.SET_COOKIE, null);
		return new ResponseEntity<>(headers, HttpStatus.OK);
	}
	
	
}
