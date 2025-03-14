package poly.edu.Assignment.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import poly.edu.Assignment.model.ProductCategory;

public interface ProductCategoryDAO extends JpaRepository<ProductCategory,Integer>{
    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name, int id);
}
