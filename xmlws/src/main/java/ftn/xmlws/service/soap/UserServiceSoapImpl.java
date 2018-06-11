package ftn.xmlws.service.soap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.xmlws.domain.User;
import ftn.xmlws.repository.UserRepository;

@Service
public class UserServiceSoapImpl implements UserServiceSoap {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public User verifyAgentLogin(String username) {
		return userRepository.findByuserName(username);
	}

}
