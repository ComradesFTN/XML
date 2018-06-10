package ftn.xmlws.service;

import java.util.List;

import ftn.xmlws.domain.AdminCode;


public interface AdminCodeService {
	
	List<AdminCode> findAll();
	
	AdminCode findOne(Long id);
	
	AdminCode save(AdminCode aCode);
	
	AdminCode delete(Long id);

}
