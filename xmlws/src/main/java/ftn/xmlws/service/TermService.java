package ftn.xmlws.service;

import java.util.List;

import ftn.xmlws.domain.Term;

public interface TermService {

	public List<Term> findAll();
	public Term findOne(Long id);
	public Term saveTerm(Term term);
	public Term deleteTerm(Long id);
	
	
}
