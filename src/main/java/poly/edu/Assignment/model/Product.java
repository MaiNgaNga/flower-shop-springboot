package poly.edu.Assignment.model;

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

}
