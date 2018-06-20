package ftn.xmlws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.xmlws.domain.AccomodationType;

@Repository
public interface AccomodationTypeRepository extends JpaRepository<AccomodationType, Long> {
	
	public AccomodationType findByType(String type);

}
