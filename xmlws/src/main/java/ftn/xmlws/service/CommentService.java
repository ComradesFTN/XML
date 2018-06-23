package ftn.xmlws.service;

import java.util.List;

import ftn.xmlws.domain.Comment;

public interface CommentService {
	
	public List<Comment> findAll();
	public Comment findOne(Long id);
	public Comment saveComment(Comment comment);
	public Comment deleteComment(Long id);


}
