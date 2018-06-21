package ftn.xmlws.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.xmlws.domain.Term;
import ftn.xmlws.repository.TermRepository;

@Service
public class TermServiceImpl implements TermService {
	
	@Autowired
	TermRepository termRepository;

	@Override
	public List<Term> findAll() {
		return termRepository.findAll();
	}

	@Override
	public Term findOne(Long id) {
		Optional<Term> termOp = termRepository.findById(id);
		return termOp.get();
	}

	@Override
	public Term saveTerm(Term term) {
		return termRepository.save(term);
	}

	@Override
	public Term deleteTerm(Long id) {
		Term t = this.findOne(id);
		if(t == null){
			throw new IllegalArgumentException("Tried to delete"
					+ "non-existant t");
		}
		termRepository.delete(t);
		return t;
	}

}
