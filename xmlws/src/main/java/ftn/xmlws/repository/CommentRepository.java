package ftn.xmlws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.xmlws.domain.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

}
