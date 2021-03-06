package ftn.xmlws.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ftn.xmlws.domain.Accomodation;
import ftn.xmlws.domain.AccomodationType;
import ftn.xmlws.domain.Category;
import ftn.xmlws.domain.Comment;
import ftn.xmlws.domain.ExtraService;
import ftn.xmlws.dto.CommentDTO;
import ftn.xmlws.service.AccomodationService;
import ftn.xmlws.service.AdminService;
import ftn.xmlws.service.CommentService;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {

	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);	
	
	@Autowired
	AdminService aService;

	@Autowired
	AccomodationService accomService;

	@Autowired
	CommentService commentService;

	@RequestMapping(value = "getAccomTypes", method = RequestMethod.GET)
	public ResponseEntity<List<AccomodationType>> getAccomTypes() {
		List<AccomodationType> aTypes = aService.findAllAccomoations();
		logger.info("Getting accomodation types.", aTypes);
		return new ResponseEntity<>(aTypes, HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "addAccomodationType", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<AccomodationType> addAccomType(@RequestBody AccomodationType accomodationType) {
		AccomodationType newAccomType = aService.saveAcomType(accomodationType);
		logger.info("Adding accomodation type.", newAccomType);
		return new ResponseEntity<>(newAccomType, HttpStatus.OK);

	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<AccomodationType> deleteAccomType(@PathVariable Long id) {
		boolean exist = false;
		List<Accomodation> provera = accomService.findAllAccomodations();
		AccomodationType type = aService.findOneAcomType(id);
		for (int i = 0; i < provera.size(); i++) {
			if (provera.get(i).getAccomodationType().equals(type)) {
				exist = true;
				break;
			}
		}
		if (exist == false) {
			AccomodationType deleted = aService.deleteAcomType(id);
			logger.info("Accomodation deleted.");
			return new ResponseEntity<>(deleted, HttpStatus.OK);
		} else {
			logger.error("Accomodation doesn't exist.");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

	@RequestMapping(value = "getCategories", method = RequestMethod.GET)
	public ResponseEntity<List<Category>> getCategories() {
		List<Category> categoryTypes = aService.findAllCategories();
		logger.info("Getting all categories.");
		return new ResponseEntity<>(categoryTypes, HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "addCategory", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Category> addCategory(@RequestBody Category category) {
		Category newCategory = aService.saveCategory(category);
		logger.info("Adding a category.");
		return new ResponseEntity<>(newCategory, HttpStatus.OK);

	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "category/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Category> deleteCategory(@PathVariable Long id) {
		boolean exist = false;
		List<Accomodation> provera = accomService.findAllAccomodations();
		Category cat = aService.findOneCategory(id);
		for (int i = 0; i < provera.size(); i++) {
			if (provera.get(i).getCategory().equals(cat)) {
				exist = true;
				break;
			}
		}
		if (exist == false) {
			Category deleted = aService.deleteCategory(id);
			logger.info("Category deleted.");
			return new ResponseEntity<>(deleted, HttpStatus.OK);
			
		} else {
			logger.error("Category not found.");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "getExtraServices", method = RequestMethod.GET)
	public ResponseEntity<List<ExtraService>> getExtraServices() {
		List<ExtraService> extraServices = aService.findAllExtraServices();
		logger.info("Getting all extra services.");
		return new ResponseEntity<>(extraServices, HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "addExtraService", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<ExtraService> addExtraServices(@RequestBody ExtraService extraService) {
		ExtraService newExtraService = aService.saveExtraService(extraService);
		logger.info("Adding an extra service.");
		return new ResponseEntity<>(newExtraService, HttpStatus.OK);

	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "extraService/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<ExtraService> deleteExtraService(@PathVariable Long id) {
		boolean exist = false;
		List<Accomodation> provera = accomService.findAllAccomodations();
		ExtraService es = aService.findOneExtraService(id);
		for (int i = 0; i < provera.size(); i++) {
			for (ExtraService e : provera.get(i).getExtraServices()) {
				if (e == es) {
					exist = true;
					break;
				}
			}
		}
		if (exist == false) {
			ExtraService deleted = aService.deleteExtraService(id);
			logger.info("Extra service deleted.");
			return new ResponseEntity<>(deleted, HttpStatus.OK);
		} else {
			logger.error("Extra service not found.");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/getAllComments", method = RequestMethod.GET)
	public ResponseEntity<List<CommentDTO>> getComments() {
		
		List<Comment> comments = commentService.findAll();
		List<CommentDTO> commentDTOList = new ArrayList<CommentDTO>();
		
 		for(Comment c : comments) {
 			if(!(c.isApproved())) {
 				CommentDTO commentDTO = new CommentDTO();
 				commentDTO.setComment(c.getText());
 				commentDTO.setUserName(c.getUser().getName());
 				commentDTO.setCommentId(c.getId());
 				commentDTOList.add(commentDTO);
 			}
			
		}
 		logger.info("Getting all comments.");
		return new ResponseEntity<>(commentDTOList, HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "disapproveComment/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Comment> disapproveComment(@PathVariable Long id) {
		Comment deleted = commentService.deleteComment(id);
		logger.info("Comment disapproved.");
		return new ResponseEntity<>(HttpStatus.OK);

	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "approveComment/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Comment> approveComment(@PathVariable Long id) {
		Comment approved = commentService.findOne(id);
		approved.setApproved(true);
		commentService.saveComment(approved);
		logger.info("Comment approved");
		return new ResponseEntity<>(HttpStatus.OK);

	}

}
