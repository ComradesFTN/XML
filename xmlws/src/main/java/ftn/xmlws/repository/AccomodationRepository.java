package ftn.xmlws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.xmlws.domain.Accomodation;

@Repository
public interface AccomodationRepository extends JpaRepository<Accomodation, Long> {

}
