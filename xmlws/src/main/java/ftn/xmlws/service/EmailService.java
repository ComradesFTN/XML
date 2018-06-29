package ftn.xmlws.service;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import ftn.xmlws.controller.UserController;
import ftn.xmlws.domain.User;

import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Service
public class EmailService {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private JavaMailSender javaMailSender;
	@Autowired
	private Environment env;
	@Autowired
	private UserService service;

	@Async
	public void send(User user) throws MailException, InterruptedException {

		String token = UUID.randomUUID().toString();
		String confirmationUrl = "/user/registrationConfirm?token=" + token;
		service.createVerificationToken(user, token);

		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(user.getEmail());
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Potvrdite email xml");
		mail.setText("Pozdrav " + user.getName() + ",\n\n Aktivacioni link je " + "http://localhost:8080/xmlws"
				+ confirmationUrl);
		javaMailSender.send(mail);

		logger.info("Registration email sent.");

	}

	@Async
	public void resetPassInfo(User user) throws MailException, InterruptedException {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(user.getEmail());
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Reset lozinke xml");
		mail.setText("Pozdrav " + user.getName() + ",\n\n Vas promenjeni password je: " + user.getPassword());
		javaMailSender.send(mail);
		logger.info("User " + user.getId() + " has requested a password reset!");
	}

}
