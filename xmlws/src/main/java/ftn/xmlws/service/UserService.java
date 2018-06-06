package ftn.xmlws.service;
import java.util.List;

import ftn.xmlws.domain.User;

public interface UserService {
	
	List<User> findAll();
	
	User findOne(Long id);
	
	User save(User userAd);
	
	User delete(Long id);

}
