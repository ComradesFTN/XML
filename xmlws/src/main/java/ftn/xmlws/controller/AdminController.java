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

import ftn.xmlws.domain.AccomodationType;

import ftn.xmlws.service.AdminService;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

	@Autowired
	AdminService aService;
	
	@RequestMapping(value = "getAccomTypes", method = RequestMethod.GET )
	public ResponseEntity<List<AccomodationType>> getAccomTypes() {
		List<AccomodationType> aTypes = aService.findAllAccomoations();
		return new ResponseEntity<>(aTypes, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<AccomodationType> addAccomType(@RequestBody AccomodationType accomodationType) {
		AccomodationType newAccomType = aService.saveAcomType(accomodationType);
		return new ResponseEntity<>(newAccomType, HttpStatus.OK);

	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<AccomodationType> deleteAccomType(@PathVariable Long id) {
		AccomodationType deleted = aService.deleteAcomType(id);
		return new ResponseEntity<>(deleted, HttpStatus.OK);
	}
}
