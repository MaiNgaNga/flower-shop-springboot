package poly.edu.Assignment.Service;
import java.util.List;

import poly.edu.Assignment.model.Discount;
import poly.edu.Assignment.model.Product;

public interface DiscountService {
    List<Discount> findAll();

    Discount findByProduct(Product product);

    Discount save(Discount discount);

    void deleteById(Long id);  

    double getDiscountedPrice(Product product);
} 
