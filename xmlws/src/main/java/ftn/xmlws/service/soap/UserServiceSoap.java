package ftn.xmlws.service.soap;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import ftn.xmlws.domain.User;

@WebService
public interface UserServiceSoap {
	
/*	@WebMethod
	@RequestWrapper(className="java.lang.String")
	@ResponseWrapper(className="ftn.xmlws.domain.User")
	@WebResult(name="returnedUser")*/
	public User verifyAgentLogin(String username);

}
