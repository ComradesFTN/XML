package ftn.xmlws.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ftn.xmlws.domain.Accomodation;
import ftn.xmlws.domain.AccomodationImage;
import ftn.xmlws.domain.AccomodationType;
import ftn.xmlws.domain.Category;
import ftn.xmlws.domain.Comment;
import ftn.xmlws.domain.ExtraService;
import ftn.xmlws.domain.MonthPrice;
import ftn.xmlws.domain.Term;
import ftn.xmlws.dto.SearchDTO;
import ftn.xmlws.dto.SearchResultDTO;
import ftn.xmlws.repository.AccomodationRepository;
import ftn.xmlws.repository.AccomodationTypeRepository;
import ftn.xmlws.repository.CategoryRepository;
import ftn.xmlws.repository.ExtraServiceRepository;
import ftn.xmlws.service.AccomodationService;
import ftn.xmlws.service.AdminService;
import ftn.xmlws.service.TermService;

@RestController
public class SearchController {

	@Autowired
	AccomodationRepository accomodationRepository;

	@Autowired
	AdminService aService;

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	AccomodationTypeRepository accomodationTypeRepository;

	@Autowired
	ExtraServiceRepository extraServiceRepository;

	@Autowired
	TermService termService;
	
	@Autowired
	AccomodationService accomodationService;
	
	private List<SearchResultDTO> searchResultDTOList;

	@SuppressWarnings("deprecation")
	@RequestMapping(value = "search", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<List<SearchResultDTO>> getAccomodations(@RequestBody SearchDTO searchDTO) throws ParseException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate = sdf.parse(searchDTO.getStartDate());
	    Date endDate = sdf.parse(searchDTO.getEndDate());
	    
	    
		
		searchResultDTOList = new ArrayList<SearchResultDTO>();
		List<Accomodation> resultAccomodations = new ArrayList<Accomodation>();
		List<Accomodation> accomodations = accomodationRepository.findAll(); // dobijes sve
		Optional<Category> catOptional = null;
		Category category = null;
		if(searchDTO.getCategory() != null) {
			catOptional = categoryRepository.findById(Long.parseLong(searchDTO.getCategory()));
			category = catOptional.get();
		}
		
		Optional<AccomodationType> accomodationTypeOp = null;		
		AccomodationType accomodationType = null; 
		if(searchDTO.getAccomodationType() != null) {
			accomodationTypeOp = accomodationTypeRepository.findById(Long.parseLong(searchDTO.getAccomodationType()));
			accomodationType = accomodationTypeOp.get();
		}
		
		Optional<ExtraService> extraServiceOp = null;
		ExtraService extraServices = null;
		Set<ExtraService> extraServicesList = new HashSet<ExtraService>();
		List<String> dtoServiceList = searchDTO.getExtraServices();
		if(dtoServiceList != null) {
			for(String s : dtoServiceList) {
				extraServiceOp = extraServiceRepository.findById(Long.parseLong(s));
				extraServices = extraServiceOp.get();
				extraServicesList.add(extraServices);
			}
		}
		
		for (Accomodation ac : accomodations) {
			boolean ok = true;
			
			if(ac.getPricePlan().isEmpty()) {
				ok = false;
			}
			
			
			
			if (category != null) {
				if (!(ac.getCategory().equals(category))) {
					ok = false;
				}

			}

			if (accomodationType != null) {
				if (!(ac.getAccomodationType().equals(accomodationType))) {
					ok = false;
				}

			}

			if (!(extraServicesList.isEmpty())) {
				if (!(ac.getExtraServices().equals(extraServicesList))) {
					ok = false;
				}

			}
			 

			if (searchDTO.getCountry() != null) {
				if (!(ac.getCountry().equals(searchDTO.getCountry()))) {
					ok = false;
				}

			}

			if (searchDTO.getCapacity() != null) {
				if (!(Integer.toString(ac.getCapacity()).equals(searchDTO.getCapacity()))) {
					ok = false;
				}

			}
			
			for(Term t : ac.getTerms()) {
				if(!((t.getStartDate().after(startDate) && t.getStartDate().after(endDate)) || 
						(t.getEndDate().before(startDate) && t.getEndDate().before(endDate)))) {
					ok = false;
				}
			}

			if (ok) {
				resultAccomodations.add(ac);
			}
		}

		for(Accomodation resAc : resultAccomodations) {
			SearchResultDTO searchRes = new SearchResultDTO();
			searchRes.setCategory(resAc.getCategory().getCategory());
			searchRes.setDescription(resAc.getDescription());
			searchRes.setId(resAc.getId());
			searchRes.setName(resAc.getName());
			searchRes.setStartDate(searchDTO.getStartDate());
			searchRes.setEndDate(searchDTO.getEndDate());
			for(AccomodationImage img : resAc.getImages()){
				searchRes.getImages().add(img.getUrl());
			}
			
			for(Comment c : resAc.getComments()) {
				if(c.isApproved()) {
					searchRes.getComments().add(c.getText());
				}
			}
			

			Calendar cStart = Calendar.getInstance(); 
			cStart.setTime(startDate);
			Calendar cEnd = Calendar.getInstance(); 
			cEnd.setTime(endDate);
			
			float sum = 0;
			
			while (cStart.before(cEnd) || cStart.equals(cEnd)) {

			    ArrayList<MonthPrice> list = new ArrayList<>(resAc.getPricePlan());		
			    list.sort(Comparator.comparing(MonthPrice::getId));			    
			    float monthPrice = list.get(cStart.get(Calendar.MONTH)).getPrice();
			    sum += monthPrice;
			    cStart.add(Calendar.DAY_OF_MONTH, 1);

			}
			
			sum *= resAc.getCapacity();
			searchRes.setTotalPrice(sum);
			long sumRating = 0;
			double ratingResult = 0;
			
			if(!(resAc.getRating().isEmpty())) {
				for(int i = 0; i < resAc.getRating().size(); i++) {
					sumRating += resAc.getRating().get(i);
				}
				
				ratingResult = sumRating / resAc.getRating().size();
			}
			
			searchRes.setRating(ratingResult);
			searchResultDTOList.add(searchRes);
		}
		return new ResponseEntity<>(searchResultDTOList, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/sort/{id}", method = RequestMethod.GET) 
	public ResponseEntity<List<SearchResultDTO>> sortList(@PathVariable Long id) {
		
		if(id == 1) {
			searchResultDTOList.sort(Comparator.comparing(SearchResultDTO::getTotalPrice));	
		}else if(id==2) {
			searchResultDTOList.sort(Comparator.comparing(SearchResultDTO::getTotalPrice));
			 Collections.reverse(searchResultDTOList);
		}else if(id==3) {
			searchResultDTOList.sort(Comparator.comparing(SearchResultDTO::getRating));
		}else if(id==4) {
			searchResultDTOList.sort(Comparator.comparing(SearchResultDTO::getRating));
			 Collections.reverse(searchResultDTOList);
		}else if(id==5) {
			searchResultDTOList.sort(Comparator.comparing(SearchResultDTO::getCategory));
		}
		
		return new ResponseEntity<>(searchResultDTOList, HttpStatus.OK);
	}
	

}
