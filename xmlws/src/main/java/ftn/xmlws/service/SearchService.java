package ftn.xmlws.service;

import java.util.List;

import ftn.xmlws.domain.Accomodation;
import ftn.xmlws.domain.Category;

public interface SearchService {
	
	public List<Accomodation> findByAgent (Long agent);
	public List<Accomodation> findByCountry(String country);
	public List<Accomodation> findByCapacity(int capacity);
	public List<Accomodation> findByCategory(Category category);

}
