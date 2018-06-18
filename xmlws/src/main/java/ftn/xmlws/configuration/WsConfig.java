package ftn.xmlws.configuration;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ftn.xmlws.service.soap.AccomodationServiceSoap;
import ftn.xmlws.service.soap.UserServiceSoap;

@Configuration
public class WsConfig{
	
	@Autowired
	private Bus bus;
	
	@Autowired
	UserServiceSoap userServiceSoap;
	
	@Autowired
	AccomodationServiceSoap accomodationServiceSoap;
	
	@Bean
	public Endpoint endpoint1() {
        EndpointImpl endpoint = new EndpointImpl(bus, userServiceSoap);
        endpoint.publish("/userServiceSoap");  
        return endpoint;
    }
	
	@Bean
	public Endpoint endpoint2() {
        EndpointImpl endpoint = new EndpointImpl(bus, accomodationServiceSoap);
        endpoint.publish("/accomodationServiceSoap");  
        return endpoint;
    }

}
