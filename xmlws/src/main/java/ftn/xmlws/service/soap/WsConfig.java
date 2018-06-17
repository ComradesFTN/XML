package ftn.xmlws.service.soap;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WsConfig {

	@Autowired
	private Bus bus;
	
	@Autowired
	UserServiceSoap userServiceSoap;
	
	public Endpoint endpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, userServiceSoap);
        endpoint.publish("/userServiceSoap");  
        return endpoint;
    }

}
