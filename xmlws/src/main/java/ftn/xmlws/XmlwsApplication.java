package ftn.xmlws;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import ftn.xmlws.config.Config;

@SpringBootApplication
public class XmlwsApplication implements WebApplicationInitializer {

	public static void main(String[] args) {
		SpringApplication.run(XmlwsApplication.class, args);
	}

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(Config.class);
		servletContext.addListener(new ContextLoaderListener(rootContext));

		AnnotationConfigWebApplicationContext webContext = new AnnotationConfigWebApplicationContext();
		webContext.setParent(rootContext);

		ServletRegistration.Dynamic cxfDispatcher = servletContext.addServlet("CXF dispatcher", CXFServlet.class);
		cxfDispatcher.setLoadOnStartup(1);
		cxfDispatcher.addMapping("/services/*");

	}
}
