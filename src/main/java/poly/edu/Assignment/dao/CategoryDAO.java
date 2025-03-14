package poly.edu.Assignment.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import poly.edu.Assignment.model.Category;

public interface CategoryDAO extends JpaRepository<Category,Integer> {
     boolean existsByName(String name);
     boolean existsByNameAndIdNot(String name, int id);
}
