package ftn.xmlws.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.naming.directory.SearchResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ftn.xmlws.domain.Accomodation;
import ftn.xmlws.domain.AccomodationType;
import ftn.xmlws.domain.Category;
import ftn.xmlws.domain.ExtraService;
import ftn.xmlws.dto.SearchDTO;
import ftn.xmlws.repository.AccomodationRepository;
import ftn.xmlws.repository.AccomodationTypeRepository;
import ftn.xmlws.repository.CategoryRepository;
import ftn.xmlws.repository.ExtraServiceRepository;
import ftn.xmlws.service.AdminService;
import ftn.xmlws.dto.SearchResultDTO;

@Controller
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

	@RequestMapping(value = "search", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<List<SearchResultDTO>> getAccomodations(@RequestBody SearchDTO searchDTO) {
		
		List<SearchResultDTO> searchResultDTOList = new ArrayList<SearchResultDTO>();
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
		//List<Optional<ExtraService>> extraServiceOpList = null;
		ExtraService extraServices = null;
		Set<ExtraService> extraServicesList = new HashSet<ExtraService>();
		List<String> dtoServiceList = searchDTO.getExtraServices();
		if(dtoServiceList != null) {
			for(String s : dtoServiceList) {
				extraServiceOp = extraServiceRepository.findById(Long.parseLong(s));
				extraServices = extraServiceOp.get();
				extraServicesList.add(extraServices);
			}
			
			//s nekim listama ima se drkas
			//extraServiceOp = extraServiceRepository.findById()
		}
		
		for (Accomodation ac : accomodations) {
			boolean ok = true;
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

			//mora se porediti el od seta i od liste
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
			searchResultDTOList.add(searchRes);
		}
		return new ResponseEntity<>(searchResultDTOList, HttpStatus.OK);
	}

}
