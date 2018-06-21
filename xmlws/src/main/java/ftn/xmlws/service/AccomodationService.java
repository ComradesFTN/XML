package ftn.xmlws.service;

import java.util.List;

import ftn.xmlws.domain.Accomodation;

public interface AccomodationService {
	
	public List<Accomodation> findAllAccomodations();
	public Accomodation findOne(Long id);
	public Accomodation saveAccomodation(Accomodation ac);
	public Accomodation deleteAccomodation(Long id);
}
