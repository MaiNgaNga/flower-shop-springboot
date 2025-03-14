package poly.edu.Assignment.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

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
    
    public double getTotal() {
        return price * quantity;
    }
}