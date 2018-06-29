package ftn.xmlws.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/security")
public class SecurityController {

	private static final Logger logger = LoggerFactory.getLogger(SecurityController.class);

	@RequestMapping(value = "/username", method = RequestMethod.GET)
	@ResponseBody
	public String currentUserName(Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		logger.info("User has authorities: " + userDetails.getAuthorities());
		return authentication.getName();
	}
}