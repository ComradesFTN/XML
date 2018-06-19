package ftn.xmlws.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.xmlws.domain.AccomodationType;
import ftn.xmlws.domain.Category;
import ftn.xmlws.domain.ExtraService;
import ftn.xmlws.repository.AccomodationTypeRepository;
import ftn.xmlws.repository.CategoryRepository;
import ftn.xmlws.repository.ExtraServiceRepository;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	AccomodationTypeRepository acomTypeRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	ExtraServiceRepository extraServiceRepository;

	@Override
	public List<AccomodationType> findAllAccomoations() {
		return acomTypeRepository.findAll();
	}

	@Override
	public List<Category> findAllCategories() {
		return categoryRepository.findAll();
	}

	@Override
	public List<ExtraService> findAllExtraServices() {
		return extraServiceRepository.findAll();
	}

	@Override
	public AccomodationType findOneAcomType(Long id) {
		Optional<AccomodationType> acomType = acomTypeRepository.findById(id);
		return acomType.get();
	}

	@Override
	public Category findOneCategory(Long id) {
		Optional<Category> cat = categoryRepository.findById(id);
		return cat.get();
	}

	@Override
	public ExtraService findOneExtraService(Long id) {
		Optional<ExtraService> extraServices = extraServiceRepository.findById(id);
		return extraServices.get();
	}

	@Override
	public AccomodationType saveAcomType(AccomodationType acomType) {
		return acomTypeRepository.save(acomType);
	}

	@Override
	public Category saveCategory(Category category) {
		return categoryRepository.save(category);
	}

	@Override
	public ExtraService saveExtraService(ExtraService eService) {
		return extraServiceRepository.save(eService);
	}

	@Override
	public AccomodationType deleteAcomType(Long id) {
		AccomodationType aType = this.findOneAcomType(id);
		if(aType == null){
			throw new IllegalArgumentException("Tried to delete"
					+ "non-existant aType");
		}
		acomTypeRepository.delete(aType);
		return aType;
	}

	@Override
	public Category deleteCategory(Long id) {
		Category cat = this.findOneCategory(id);
		if(cat == null){
			throw new IllegalArgumentException("Tried to delete"
					+ "non-existant cat");
		}
		categoryRepository.delete(cat);
		return cat;
	}

	@Override
	public ExtraService deleteExtraService(Long id) {
		ExtraService eService = this.findOneExtraService(id);
		if(eService == null){
			throw new IllegalArgumentException("Tried to delete"
					+ "non-existant eService");
		}
		extraServiceRepository.delete(eService);
		return eService;
	}

}
