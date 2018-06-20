package ftn.xmlws.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.xmlws.domain.Accomodation;
import ftn.xmlws.domain.Category;

@Repository
public interface AccomodationRepository extends JpaRepository<Accomodation, Long> {
	
	public List<Accomodation> findByAgent (Long agent);
	public List<Accomodation> findByCountry(String country);
	public List<Accomodation> findByCapacity(int capacity);
	public List<Accomodation> findByCategory(Category category);
}
