package ftn.xmlws.service.soap;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.xmlws.domain.Accomodation;
import ftn.xmlws.repository.AccomodationImageRepository;
import ftn.xmlws.repository.AccomodationRepository;
import ftn.xmlws.repository.AccomodationTypeRepository;
import ftn.xmlws.repository.CategoryRepository;
import ftn.xmlws.repository.ExtraServicesRepository;
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
	private ExtraServicesRepository extraServicesRepository;
	
	@Autowired
	private TermRepository termRepository;
	
	@Override
	public Accomodation save(Accomodation acc) {			
		return accomodationRepository.save(acc);
	}
	
	

}
