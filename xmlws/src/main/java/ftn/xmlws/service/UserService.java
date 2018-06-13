package ftn.xmlws.service;
import java.util.List;

import ftn.xmlws.domain.User;
import ftn.xmlws.domain.VerificationToken;


public interface UserService {
	
	List<User> findAll();
	
	User findOne(Long id);
	
	User save(User userAd);
	
	User delete(Long id);
	
	void createVerificationToken(User user, String token);

	VerificationToken getVerificationToken(String VerificationToken);
	
	User getUserByEmail(String email);

}
