package ftn.xmlws.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.xmlws.domain.AdminCode;
import ftn.xmlws.repository.AdminCodeRepository;


@Service
public class AdminCodeServiceImpl implements AdminCodeService{

	@Autowired
	public AdminCodeRepository aCodeRepository;

	@Override
	public List<AdminCode> findAll() {
		return aCodeRepository.findAll();
	}

	@Override
	public AdminCode findOne(Long id) {
		Optional<AdminCode> optAdm = aCodeRepository.findById(id);		
		return optAdm.get();
	}

	@Override
	public AdminCode save(AdminCode aCode) {
		return aCodeRepository.save(aCode);
	}

	@Override
	public AdminCode delete(Long id) {
		AdminCode aCode = this.findOne(id);
		if(aCode == null){
			throw new IllegalArgumentException("Tried to delete"
					+ "non-existant userAd");
		}
		aCodeRepository.delete(aCode);
		return aCode;
	}

}
