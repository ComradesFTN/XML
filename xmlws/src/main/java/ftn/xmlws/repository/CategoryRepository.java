package ftn.xmlws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.xmlws.domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
