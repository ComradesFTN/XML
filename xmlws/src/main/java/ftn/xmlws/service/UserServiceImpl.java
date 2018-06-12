package ftn.xmlws.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.xmlws.domain.User;
import ftn.xmlws.domain.VerificationToken;
import ftn.xmlws.repository.UserRepository;
import ftn.xmlws.repository.VerificationTokenRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	public UserRepository userRepository;
	
	@Autowired
	private VerificationTokenRepository tokenRepository;

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User findOne(Long id) {
		return userRepository.findOne(id);
	}

	@Override
	public User save(User userAd) {
		return userRepository.save(userAd);
	}

	@Override
	public User delete(Long id) {
		User user = userRepository.findOne(id);
		if(user == null){
			throw new IllegalArgumentException("Tried to delete"
					+ "non-existant userAd");
		}
		userRepository.delete(user);
		return user;
	}

	@Override
	public void createVerificationToken(User user, String token) {
		VerificationToken myToken = new VerificationToken(user, token);
        tokenRepository.save(myToken);
		
	}

	@Override
	public VerificationToken getVerificationToken(String VerificationToken) {
		
		return tokenRepository.findByToken(VerificationToken);
	}

}
