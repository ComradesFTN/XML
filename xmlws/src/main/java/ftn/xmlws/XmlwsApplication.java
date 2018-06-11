package ftn.xmlws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource({ "classpath:cxf.xml" })
public class XmlwsApplication {

	public static void main(String[] args) {
		SpringApplication.run(XmlwsApplication.class, args);
	}
}
