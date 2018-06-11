package ftn.xmlws.service.soap;

import javax.jws.WebMethod;
import javax.jws.WebService;

import ftn.xmlws.domain.User;

@WebService
public interface UserServiceSoap {
	
	@WebMethod
	public User verifyAgentLogin(String username);

}
