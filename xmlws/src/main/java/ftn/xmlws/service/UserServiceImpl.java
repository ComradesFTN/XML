package ftn.xmlws.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.xmlws.domain.User;
import ftn.xmlws.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	public UserRepository userRepository;

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

}
