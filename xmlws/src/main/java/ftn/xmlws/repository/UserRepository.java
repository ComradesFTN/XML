package ftn.xmlws.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.xmlws.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	public Optional<User> findByuserName (String username);
	
	public User findByEmail(String email);

}
