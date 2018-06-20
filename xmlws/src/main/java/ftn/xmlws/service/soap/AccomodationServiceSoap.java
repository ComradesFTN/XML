package ftn.xmlws.service.soap;

import java.util.List;

import javax.jws.WebService;

import ftn.xmlws.domain.Accomodation;
import ftn.xmlws.domain.AccomodationImage;
import ftn.xmlws.domain.AccomodationType;
import ftn.xmlws.domain.Category;
import ftn.xmlws.domain.ExtraService;
import ftn.xmlws.domain.MonthPrice;
import ftn.xmlws.domain.Term;
import ftn.xmlws.dto.AccomodationSoapDTO;

@WebService
public interface AccomodationServiceSoap {
	
	public List<AccomodationType> findAllTypes();
	public List<Category> findAllCategories();
	public List<ExtraService> findAllServices();
	public List<AccomodationSoapDTO> findAllAccomodations();
	public List<MonthPrice> findAllMonthPrices();
	
	public Accomodation save(Accomodation acc);		
	public MonthPrice saveMP(MonthPrice mp,Long accId);

	public Accomodation findAccomodationById(Long id);
	public AccomodationImage findImageById(Long id);
	public AccomodationType findTypeById(Long id);
	public Category findCategoryById(Long id);
	public ExtraService findExtraServiceById(Long id);
	public Term findTermById(Long id);
	public MonthPrice findMonthPriceById(Long id);
	
	public List<Accomodation> getAccomodationsOfAgent(Long id);
}
