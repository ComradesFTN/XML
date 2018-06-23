package ftn.xmlws.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.xmlws.domain.Comment;
import ftn.xmlws.repository.CommentRepository;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	CommentRepository commentRepository;

	@Override
	public List<Comment> findAll() {
		return commentRepository.findAll();
	}

	@Override
	public Comment findOne(Long id) {
		Optional<Comment> commentOp = commentRepository.findById(id);
		return commentOp.get();
	}

	@Override
	public Comment saveComment(Comment comment) {
		return commentRepository.save(comment);
	}

	@Override
	public Comment deleteComment(Long id) {
		Comment c = this.findOne(id);
		if(c == null){
			throw new IllegalArgumentException("Tried to delete"
					+ "non-existant t");
		}
		commentRepository.delete(c);
		return c;
	}

}
