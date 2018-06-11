package ftn.xmlws.config;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import ftn.xmlws.service.soap.UserServiceSoapImpl;

@Configuration
@ImportResource({ "classpath:cxf.xml" })
public class Config {

	@Autowired
    private Bus cxfBus;
	
	@Autowired
	private UserServiceSoapImpl userServiceSoapImpl;
		
	@Bean
    public EndpointImpl UserServiceSoap() {
		EndpointImpl endpoint = new EndpointImpl(cxfBus, userServiceSoapImpl);
        //endpoint.publish("/UserServiceSoap");
        return endpoint;
    }
	
    @Bean
    public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}
