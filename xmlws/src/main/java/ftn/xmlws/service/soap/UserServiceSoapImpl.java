package ftn.xmlws.service.soap;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.xmlws.domain.User;
import ftn.xmlws.repository.UserRepository;

@Service
@WebService(targetNamespace="http://xmlws/", serviceName="UserService", endpointInterface="ftn.xmlws.service.soap.UserServiceSoap")
public class UserServiceSoapImpl implements UserServiceSoap {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public User verifyAgentLogin(String username) {
		return userRepository.findByuserName(username).get();
	}

	@Override
	public User findOne(Long id) {
		return userRepository.findById(id).get();
	}

}
