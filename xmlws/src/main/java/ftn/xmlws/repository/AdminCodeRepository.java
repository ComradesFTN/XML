package ftn.xmlws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.xmlws.domain.AdminCode;

@Repository
public interface AdminCodeRepository extends JpaRepository<AdminCode, Long>{

}
