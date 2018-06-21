package ftn.xmlws.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ftn.xmlws.domain.Accomodation;
import ftn.xmlws.domain.AccomodationType;
import ftn.xmlws.domain.Term;
import ftn.xmlws.domain.User;
import ftn.xmlws.dto.TermDTO;
import ftn.xmlws.service.AccomodationService;
import ftn.xmlws.service.AdminService;
import ftn.xmlws.service.TermService;
import ftn.xmlws.service.UserService;

@RequestMapping(value = "/term")
public class TermController {

	@Autowired
	TermService termService;

	@Autowired
	UserService userService;

	@Autowired
	AccomodationService accomodationService;

	@SuppressWarnings({ "deprecation", "static-access" })
	@RequestMapping(value = "create", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<TermDTO> createTerm(@RequestBody TermDTO termDTO) throws ParseException {

		Accomodation accomodation = accomodationService.findOne(Long.parseLong(termDTO.getAccomodationId()));
		User user = userService.findOne(Long.parseLong(termDTO.getUserId()));

		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate = new Date();		
		startDate = formatter.parse(termDTO.getStartDate());
		
		Date endDate = new Date();
		endDate = formatter.parse(termDTO.getEndDate());

		Term newTerm = new Term();
		newTerm.setAccomodation(accomodation);
		newTerm.setUser(user);
		newTerm.setStartDate(startDate);
		newTerm.setEndDate(endDate);
		
		termService.saveTerm(newTerm);
		termDTO.setAccomodationName(accomodation.getName());
		return new ResponseEntity<>(termDTO, HttpStatus.OK);

	}

}
