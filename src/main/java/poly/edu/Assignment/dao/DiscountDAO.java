package poly.edu.Assignment.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import poly.edu.Assignment.model.Discount;
import poly.edu.Assignment.model.Product;

public interface DiscountDAO extends JpaRepository<Discount, Long>{
    Discount findByProduct(Product product);
}
