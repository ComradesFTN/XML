package ftn.xmlws.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ftn.xmlws.domain.AdminCode;
import ftn.xmlws.service.AdminCodeService;

@RestController
@RequestMapping(value = "/aCodes")
public class AdminCodeController {
	
	@Autowired
	private AdminCodeService aCodeService;
	
	@RequestMapping(value = "getACodes", method = RequestMethod.GET )
	public ResponseEntity<List<AdminCode>> getACodes() {
		List<AdminCode> aCode = aCodeService.findAll();
		return new ResponseEntity<>(aCode, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<AdminCode> addACode(@RequestBody AdminCode aCode) {
		AdminCode newACode = aCodeService.save(aCode);
		return new ResponseEntity<>(newACode, HttpStatus.OK);

	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<AdminCode> deleteACode(@PathVariable Long id) {
		AdminCode deleted = aCodeService.delete(id);
		return new ResponseEntity<>(deleted, HttpStatus.OK);
	}


}
