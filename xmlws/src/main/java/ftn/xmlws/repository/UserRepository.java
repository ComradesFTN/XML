package ftn.xmlws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.xmlws.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
