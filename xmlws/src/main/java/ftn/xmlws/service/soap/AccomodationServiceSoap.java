package ftn.xmlws.service.soap;

import javax.jws.WebService;

import ftn.xmlws.domain.Accomodation;

@WebService
public interface AccomodationServiceSoap {
	
	public Accomodation save(Accomodation acc);
	
}
