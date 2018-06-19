package ftn.xmlws.service.soap;

import javax.jws.WebService;

import ftn.xmlws.domain.User;

@WebService
public interface UserServiceSoap {
	
/*	@WebMethod
	@RequestWrapper(className="java.lang.String")
	@ResponseWrapper(className="ftn.xmlws.domain.User")
	@WebResult(name="returnedUser")*/
	public User verifyAgentLogin(String username);
	public User findOne(Long id);

}
