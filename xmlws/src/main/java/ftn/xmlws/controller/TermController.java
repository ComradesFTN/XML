package ftn.xmlws.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ftn.xmlws.domain.Accomodation;
import ftn.xmlws.domain.Comment;
import ftn.xmlws.domain.Term;
import ftn.xmlws.domain.User;
import ftn.xmlws.dto.CommentDTO;
import ftn.xmlws.dto.TermDTO;
import ftn.xmlws.service.AccomodationService;
import ftn.xmlws.service.CommentService;
import ftn.xmlws.service.TermService;
import ftn.xmlws.service.UserService;

@RestController
@RequestMapping(value = "/term")
public class TermController {
	
	private static final Logger logger = LoggerFactory.getLogger(SearchController.class);

	@Autowired
	TermService termService;

	@Autowired
	UserService userService;

	@Autowired
	AccomodationService accomodationService;
	
	@Autowired
	CommentService commentService;

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
		logger.info("Term creation.");
		return new ResponseEntity<>(termDTO, HttpStatus.OK);

	}
	
	@RequestMapping(value = "/getTerms/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<TermDTO>> getTerms(@PathVariable String id) {
		List<Term> terms = termService.findAll();
		List<TermDTO> listTermDTO = new ArrayList<TermDTO>();
		for(Term t : terms) {
			if((Long.parseLong(id)) == t.getUser().getId()) {
				TermDTO termDTO = new TermDTO();
				termDTO.setAccomodationId(t.getAccomodation().getId().toString());
				termDTO.setAccomodationName(t.getAccomodation().getName());
				termDTO.setEndDate(t.getEndDate().toString());
				termDTO.setStartDate(t.getStartDate().toString());
				termDTO.setTermId(t.getId());
				termDTO.setConfirmed(t.isConfirmed());
				termDTO.setRated(t.isRated());
				listTermDTO.add(termDTO);
			}
			
			
		}
		logger.info("Getting term by id. Term id: " + id + ".");
		return new ResponseEntity<>(listTermDTO, HttpStatus.OK);
	}
	

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<TermDTO> deleteTerm(@PathVariable Long id) {
		Term deleted = termService.deleteTerm(id);
		logger.info("Term deleted.");
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/saveRating/{id}/{ocena}", method = RequestMethod.PUT)
	public ResponseEntity<String> saveRating(@PathVariable Long id, @PathVariable Long ocena) {
		Term ratedTerm = termService.findOne(id);
		ratedTerm.setRated(true);
			
		Accomodation accomodation = ratedTerm.getAccomodation();
		accomodation.getRating().add(ocena);
		
		termService.saveTerm(ratedTerm);
		accomodationService.saveAccomodation(accomodation);
		logger.info("Saving a rating.");
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/addComment", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<String> addComment(@RequestBody CommentDTO commentDTO) {
		
		Term tempTerm = termService.findOne(commentDTO.getTermId());
		
		Comment newComment = new Comment();
		newComment.setText(commentDTO.getComment());
		newComment.setUser(tempTerm.getUser());
		newComment.setAccomodation(tempTerm.getAccomodation());
		tempTerm.getAccomodation().getComments().add(newComment);
		
		commentService.saveComment(newComment);
		accomodationService.saveAccomodation(tempTerm.getAccomodation());
		logger.info("Comment added: " + newComment.getText() + ".");
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getTerm/{id}", method = RequestMethod.GET)
	public ResponseEntity<TermDTO> getTerm(@PathVariable Long id) {
		Term t = termService.findOne(id);
		TermDTO termDTO = new TermDTO();
		termDTO.setUserQuestion(t.getUserQuestion());
		termDTO.setAgentAnswer(t.getAgentAnswer());
		termDTO.setTermId(t.getId());
		logger.info("Getting term");
		return new ResponseEntity<>(termDTO, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "sendMessage", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<TermDTO> sendMessage(@RequestBody TermDTO termDTO){
		Term t = termService.findOne(termDTO.getTermId());
		t.setUserQuestion(termDTO.getUserQuestion());
		termService.saveTerm(t);
		logger.info("Message sent: " + t.getUserQuestion() + ".");
		return new ResponseEntity<>(termDTO, HttpStatus.OK);

	}

}
