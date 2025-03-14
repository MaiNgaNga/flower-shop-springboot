package poly.edu.Assignment.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import poly.edu.Assignment.model.Order;

public interface OrderDAO  extends JpaRepository<Order,Long>{
     List<Order> findByUserIdOrderByIdDesc(int userId);
     List<Order> findByStatusOrderByIdDesc(String status);

   @Query("SELECT AVG(o.totalAmount) FROM Order o WHERE o.status = :status")
Double averageOrderAmount(@Param("status") String status);
@Query("SELECT SUM(o.totalAmount) FROM Order o WHERE o.status = :status ")
Double sumTotalAmount(@Param("status") String status);
@Query("SELECT SUM(o.totalAmount) FROM Order o WHERE o.status = :status AND MONTH(o.createDate) = MONTH(CURRENT_DATE) AND YEAR(o.createDate) = YEAR(CURRENT_DATE)")
Double sumTotalAmountForCurrentMonth(@Param("status") String status);

@Query("SELECT AVG(o.totalAmount) FROM Order o WHERE o.status = :status")
Double getAverageOrderValue(@Param("status") String status);

@Query("SELECT COUNT(o) FROM Order o WHERE o.status = :status")
Long getCountOrder(@Param("status") String status);

@Query("SELECT COUNT(o) FROM Order o WHERE MONTH(o.createDate) = MONTH(CURRENT_DATE) AND YEAR(o.createDate) = YEAR(CURRENT_DATE) AND o.status = :status")
Long countOrdersThisMonth(@Param("status") String status);

}
