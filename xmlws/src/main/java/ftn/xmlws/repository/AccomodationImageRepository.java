package ftn.xmlws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.xmlws.domain.AccomodationImage;

@Repository
public interface AccomodationImageRepository extends JpaRepository<AccomodationImage, Long> {

}
