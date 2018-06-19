package ftn.xmlws.service.soap;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.xmlws.domain.Accomodation;
import ftn.xmlws.domain.AccomodationImage;
import ftn.xmlws.domain.AccomodationType;
import ftn.xmlws.domain.Category;
import ftn.xmlws.domain.ExtraService;
import ftn.xmlws.domain.Term;
import ftn.xmlws.dto.AccomodationSoapDTO;
import ftn.xmlws.repository.AccomodationImageRepository;
import ftn.xmlws.repository.AccomodationRepository;
import ftn.xmlws.repository.AccomodationTypeRepository;
import ftn.xmlws.repository.CategoryRepository;
import ftn.xmlws.repository.ExtraServiceRepository;
import ftn.xmlws.repository.MonthPriceRepository;
import ftn.xmlws.repository.TermRepository;

@Service
@WebService(targetNamespace="http://xmlws/", serviceName="AccomodationService", endpointInterface="ftn.xmlws.service.soap.AccomodationServiceSoap")
public class AccomodationServiceSoapImpl implements AccomodationServiceSoap {

	@Autowired
	private AccomodationRepository accomodationRepository;
	
	@Autowired
	private AccomodationImageRepository accomodationImageRepository;
	
	@Autowired
	private AccomodationTypeRepository accomodationTypeRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ExtraServiceRepository extraServiceRepository;
	
	@Autowired
	private TermRepository termRepository;
	
	@Autowired
	private MonthPriceRepository monthPriceRepository;
	
	@Override
	public Accomodation save(Accomodation acc) {			
		return accomodationRepository.save(acc);
	}

	@Override
	public AccomodationImage findImageById(Long id) {
		return accomodationImageRepository.findById(id).get();
	}

	@Override
	public AccomodationType findTypeById(Long id) {
		return accomodationTypeRepository.findById(id).get();
	}

	@Override
	public Category findCategoryById(Long id) {
		return categoryRepository.findById(id).get();
	}

	@Override
	public ExtraService findExtraServiceById(Long id) {
		return extraServiceRepository.findById(id).get();
	}

	@Override
	public Term findTermById(Long id) {
		return termRepository.findById(id).get();
	}

	@Override
	public List<Accomodation> getAccomodationsOfAgent(Long id) {
		return accomodationRepository.findByAgent(id);
	}

	@Override
	public List<AccomodationType> findAllTypes() {
		return accomodationTypeRepository.findAll();
	}

	@Override
	public List<Category> findAllCategories() {
		return categoryRepository.findAll();
	}

	@Override
	public List<ExtraService> findAllServices() {
		return extraServiceRepository.findAll();
	}

	@Override
	public List<AccomodationSoapDTO> findAllAccomodations() {
		List<Accomodation> accomodations = accomodationRepository.findAll();
		List<AccomodationSoapDTO> accomodationsDTO = new ArrayList<AccomodationSoapDTO>();
		for(Accomodation accomodation : accomodations){
			AccomodationSoapDTO accomodationDTO = new AccomodationSoapDTO();
			accomodationDTO.setAccomodationTypeId(accomodation.getAccomodationType().getId());
			accomodationDTO.setAddress(accomodation.getAddress());
			accomodationDTO.setCapacity(accomodation.getCapacity());
			accomodationDTO.setCategoryId(accomodation.getCategory().getId());
			accomodationDTO.setCountry(accomodation.getCountry());
			accomodationDTO.setDescription(accomodation.getDescription());
			for(ExtraService es : accomodation.getExtraServices()){
				accomodationDTO.getExtraServicesIds().add(es.getId());
			}
			accomodationDTO.setName(accomodation.getName());
			accomodationDTO.setId(accomodation.getId());
			accomodationsDTO.add(accomodationDTO);
		}
		return accomodationsDTO;
	}
	
	
	
	
	
	

}
