package ftn.xmlws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class XmlwsApplication extends SpringBootServletInitializer {	
	
	public static void main(String[] args) {
		SpringApplication.run(XmlwsApplication.class, args);
	}	
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(XmlwsApplication.class);
    }
	
}
