package ftn.xmlws.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.xmlws.domain.Accomodation;
import ftn.xmlws.domain.Category;
import ftn.xmlws.repository.AccomodationRepository;

@Service
public class SearchServiceImpl implements SearchService {
	
	@Autowired
	AccomodationRepository accomodationRepository;

	@Override
	public List<Accomodation> findByAgent(Long agent) {
		return accomodationRepository.findByAgent(agent);
	}

	@Override
	public List<Accomodation> findByCountry(String country) {
		return accomodationRepository.findByCountry(country);
	}

	@Override
	public List<Accomodation> findByCapacity(int capacity) {
		return accomodationRepository.findByCapacity(capacity);
	}

	@Override
	public List<Accomodation> findByCategory(Category category) {
		return accomodationRepository.findByCategory(category);
	}

}
