package poly.edu.Assignment.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import poly.edu.Assignment.model.Comment;

public interface CommentDAO extends JpaRepository<Comment, Long>{
    List<Comment> findByProductId(Long productId);
}
