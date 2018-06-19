package ftn.xmlws.service;

import java.util.List;

import ftn.xmlws.domain.AccomodationType;
import ftn.xmlws.domain.Category;
import ftn.xmlws.domain.ExtraService;

public interface AdminService {
	
	List<AccomodationType> findAllAccomoations();
	List<Category> findAllCategories();
	List<ExtraService> findAllExtraService();
	
	AccomodationType findOneAcomType(Long id);
	Category findOneCategory(Long id);
	ExtraService findOneExtraService(Long id);
	
	AccomodationType saveAcomType(AccomodationType acomType);
	Category saveCategory(Category category);
	ExtraService saveExtraService(ExtraService eService);
	
	AccomodationType deleteAcomType(Long id);
	Category deleteCategory(Long id);
	ExtraService deleteExtraService(Long id);


}
