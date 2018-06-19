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
import ftn.xmlws.domain.Category;
import ftn.xmlws.domain.ExtraService;
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
	
	@RequestMapping(value = "addAccomodationType", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<AccomodationType> addAccomType(@RequestBody AccomodationType accomodationType) {
		AccomodationType newAccomType = aService.saveAcomType(accomodationType);
		return new ResponseEntity<>(newAccomType, HttpStatus.OK);

	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<AccomodationType> deleteAccomType(@PathVariable Long id) {
		AccomodationType deleted = aService.deleteAcomType(id);
		return new ResponseEntity<>(deleted, HttpStatus.OK);
	}
	
	@RequestMapping(value = "getCategories", method = RequestMethod.GET )
	public ResponseEntity<List<Category>> getCategories() {
		List<Category> categoryTypes = aService.findAllCategories();
		return new ResponseEntity<>(categoryTypes, HttpStatus.OK);
	}
	
	@RequestMapping(value = "addCategory", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Category> addCategory(@RequestBody Category category) {
		Category newCategory = aService.saveCategory(category);
		return new ResponseEntity<>(newCategory, HttpStatus.OK);

	}
	
	@RequestMapping(value = "category/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Category> deleteCategory(@PathVariable Long id) {
		Category deleted = aService.deleteCategory(id);
		return new ResponseEntity<>(deleted, HttpStatus.OK);
	}
	
	@RequestMapping(value = "getExtraServices", method = RequestMethod.GET )
	public ResponseEntity<List<ExtraService>> getExtraServices() {
		List<ExtraService> extraServices = aService.findAllExtraService();
		return new ResponseEntity<>(extraServices, HttpStatus.OK);
	}
	
	@RequestMapping(value = "addExtraService", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<ExtraService> addExtraServices(@RequestBody ExtraService extraService) {
		ExtraService newExtraService = aService.saveExtraService(extraService);
		return new ResponseEntity<>(newExtraService, HttpStatus.OK);

	}
	
	@RequestMapping(value = "extraService/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<ExtraService> deleteExtraService(@PathVariable Long id) {
		ExtraService deleted = aService.deleteExtraService(id);
		return new ResponseEntity<>(deleted, HttpStatus.OK);
	}
}