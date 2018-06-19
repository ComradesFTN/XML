package ftn.xmlws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.xmlws.domain.ExtraService;

@Repository
public interface ExtraServiceRepository extends JpaRepository<ExtraService, Long> {

}
