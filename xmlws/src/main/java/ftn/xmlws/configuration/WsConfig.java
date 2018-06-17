package ftn.xmlws.configuration;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ftn.xmlws.service.soap.UserServiceSoap;

@Configuration
public class WsConfig{
	
	@Autowired
	private Bus bus;
	
	@Autowired
	UserServiceSoap userServiceSoap;
	
	@Bean
	public Endpoint endpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, userServiceSoap);
        endpoint.publish("/userServiceSoap");  
        return endpoint;
    }

}
