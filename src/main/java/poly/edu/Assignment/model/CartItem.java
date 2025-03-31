package poly.edu.Assignment.model;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;
import poly.edu.Assignment.Service.DiscountService;

@Entity
@Table(name = "cart_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Min(value = 1, message = "Số lượng phải ít nhất là 1")
    private int quantity;

    private double price; 
    
    @Autowired
    @Transient
    private DiscountService discountService; // Inject DiscountService để lấy giá giảm

    public double getDiscountedPrice() {
        return discountService != null ? discountService.getDiscountedPrice(product) : price;
    }

    public double getTotal() {
        return getDiscountedPrice() * quantity;
    }
}