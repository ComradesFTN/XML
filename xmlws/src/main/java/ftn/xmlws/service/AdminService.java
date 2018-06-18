package ftn.xmlws.service;

import java.util.List;

import ftn.xmlws.domain.AccomodationType;
import ftn.xmlws.domain.Category;
import ftn.xmlws.domain.ExtraServices;

public interface AdminService {
	
	List<AccomodationType> findAllAccomoations();
	List<Category> findAllCategories();
	List<ExtraServices> findAllExtraServices();
	
	AccomodationType findOneAcomType(Long id);
	Category findOneCategory(Long id);
	ExtraServices findOneExtraService(Long id);
	
	AccomodationType saveAcomType(AccomodationType acomType);
	Category saveCategory(Category category);
	ExtraServices saveExtraService(ExtraServices eService);
	
	AccomodationType deleteAcomType(Long id);
	Category deleteCategory(Long id);
	ExtraServices deleteExtraService(Long id);


}
