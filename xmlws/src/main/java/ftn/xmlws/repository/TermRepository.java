package ftn.xmlws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.xmlws.domain.Term;

@Repository
public interface TermRepository extends JpaRepository<Term, Long> {
	

}
