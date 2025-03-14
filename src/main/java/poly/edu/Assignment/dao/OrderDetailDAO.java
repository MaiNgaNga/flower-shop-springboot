package poly.edu.Assignment.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import poly.edu.Assignment.model.Order;
import poly.edu.Assignment.model.OrderDetail;

public interface OrderDetailDAO extends JpaRepository<OrderDetail,Long> {
    List<OrderDetail> findByOrder(Order order);
    void deleteByOrderId(Long id);
   

}
