package ftn.xmlws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.xmlws.domain.MonthPrice;

@Repository
public interface MonthPriceRepository extends JpaRepository<MonthPrice, Long> {

}
