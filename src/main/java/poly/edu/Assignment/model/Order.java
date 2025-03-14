package poly.edu.Assignment.model;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
public class Order {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id", nullable = false)
    private User user;  
    
    @Temporal(TemporalType.DATE)
    private Date createDate;
    @Column
    private String sdt;

    @Column(name = "address", columnDefinition = "NVARCHAR(255)")
    private String address;

    @Column
    private Double totalAmount;

    @Column(name = "status", columnDefinition = "NVARCHAR(255)")
    private String status;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderDetail> orderDetails;
}

