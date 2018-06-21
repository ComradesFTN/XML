package ftn.xmlws.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.xmlws.domain.Accomodation;
import ftn.xmlws.repository.AccomodationRepository;

@Service
public class AccomodationServiceImpl implements AccomodationService {
	
	@Autowired
	AccomodationRepository accomodationRepository;

	@Override
	public List<Accomodation> findAllAccomodations() {
		return accomodationRepository.findAll();
	}

	@Override
	public Accomodation findOne(Long id) {
		Optional<Accomodation> accOp = accomodationRepository.findById(id);
		return accOp.get();
	}

	@Override
	public Accomodation saveAccomodation(Accomodation ac) {
		return accomodationRepository.save(ac);
	}

	@Override
	public Accomodation deleteAccomodation(Long id) {
		Accomodation a = this.findOne(id);
		if(a == null){
			throw new IllegalArgumentException("Tried to delete"
					+ "non-existant a");
		}
		accomodationRepository.delete(a);
		return a;
	}

}
