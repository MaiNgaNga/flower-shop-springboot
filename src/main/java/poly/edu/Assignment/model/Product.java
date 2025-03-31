package poly.edu.Assignment.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Entity
@Builder
@Table(name="products")
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(columnDefinition = "NVARCHAR(55)", nullable = false)
    @NotBlank(message = "Tên sản phẩm không được để trống")
    @Size(max = 55, message = "Tên sản phẩm tối đa 55 ký tự")
    private String name;

    @Column(columnDefinition = "NVARCHAR(255)")
    @Size(max = 255, message = "Mô tả sản phẩm tối đa 255 ký tự")
    private String description;

    @Positive(message = "Giá sản phẩm phải lớn hơn 0")
    private double price;
    
    @Column
    @PositiveOrZero(message = "Số lượng không được âm")
    private Integer quantity;

    
    @Column(columnDefinition = "NVARCHAR(255)")
    private String image_url;

    @ManyToOne
    @JoinColumn(name = "Category_Id", referencedColumnName = "id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "color_Id", referencedColumnName = "id")
    private Color color;

    @ManyToOne
    @JoinColumn(name = "product_Category_Id", referencedColumnName = "id") // Tạo FK
    private ProductCategory productCategory;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Discount> discounts;

    public double getDiscountPrice() {
    if (discounts == null || discounts.isEmpty()) {
        return price; // Không có giảm giá => Trả về giá gốc
    }

    // Lọc danh sách giảm giá có hiệu lực (đang hoạt động & trong khoảng thời gian hợp lệ)
    Discount activeDiscount = discounts.stream()
        .filter(d -> d.isStatus() // Giảm giá đang hoạt động
                && d.getStartDate().isBefore(LocalDateTime.now()) // Đã bắt đầu
                && d.getEndDate().isAfter(LocalDateTime.now())) // Chưa kết thúc
        .findFirst()
        .orElse(null);

    if (activeDiscount == null) {
        return price; // Không có mã giảm giá hợp lệ => Trả về giá gốc
    }

    // Tính toán giá sau giảm
    double discountPrice;
    if ("percent".equals(activeDiscount.getDiscountType())) {
        discountPrice = price * (1 - activeDiscount.getDiscountValue() / 100);
    } else if ("fixed".equals(activeDiscount.getDiscountType())) {
        discountPrice = Math.max(0, price - activeDiscount.getDiscountValue());
    } else {
        return price; // Trường hợp lỗi, trả về giá gốc
    }

    return Math.round(discountPrice * 100.0) / 100.0; // Làm tròn 2 chữ số thập phân
}
}
