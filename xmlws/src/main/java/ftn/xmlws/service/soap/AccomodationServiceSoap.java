package ftn.xmlws.service.soap;

import javax.jws.WebService;

import ftn.xmlws.domain.Accomodation;
import ftn.xmlws.domain.AccomodationImage;
import ftn.xmlws.domain.AccomodationType;
import ftn.xmlws.domain.Category;
import ftn.xmlws.domain.ExtraServices;
import ftn.xmlws.domain.Term;

@WebService
public interface AccomodationServiceSoap {
	
	public Accomodation save(Accomodation acc);	
	public AccomodationImage findImageById(Long id);
	public AccomodationType findTypeById(Long id);
	public Category findCategoryById(Long id);
	public ExtraServices findExtraServicesById(Long id);
	public Term findTermById(Long id);
}
