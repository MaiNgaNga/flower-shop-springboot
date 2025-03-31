package poly.edu.Assignment.Service.impl;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poly.edu.Assignment.Service.DiscountService;
import poly.edu.Assignment.dao.DiscountDAO;
import poly.edu.Assignment.model.Discount;
import poly.edu.Assignment.model.Product;

@Service
public class DiscountServiceImpl implements DiscountService {
    
    @Autowired
    private DiscountDAO discountDAO;
    
    @Override
    public List<Discount> findAll() {
        return discountDAO.findAll();
    }
    
    @Override
    public Discount findByProduct(Product product) {
        return discountDAO.findByProduct(product);
    }
    
    @Override
    public Discount save(Discount discount) {
        return discountDAO.save(discount);
    }
    
    @Override
    public void deleteById(Long id) {
        discountDAO.deleteById(id);
    }

    @Override
    public double getDiscountedPrice(Product product) {
    Discount discount = discountDAO.findByProduct(product);
    
    if (discount == null || !discount.isStatus() || discount.getEndDate().isBefore(LocalDateTime.now())) {
        return product.getPrice(); // Không có giảm giá hoặc hết hạn -> giá gốc
    }

    if ("percent".equalsIgnoreCase(discount.getDiscountType())) {
        return product.getPrice() * (1 - discount.getDiscountValue() / 100);
    } else { // Loại "fixed"
        return Math.max(0, product.getPrice() - discount.getDiscountValue());
    }
}

}