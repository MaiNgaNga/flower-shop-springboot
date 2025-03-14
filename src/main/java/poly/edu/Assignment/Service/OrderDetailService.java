package poly.edu.Assignment.Service;

import java.util.List;

import poly.edu.Assignment.model.Order;
import poly.edu.Assignment.model.OrderDetail;

public interface OrderDetailService {
    List<OrderDetail> getOrderDetailsByOrder(Order order);
    OrderDetail getOrderDetailById(Long id);
    List<OrderDetail> getAllOrderDetails();
    void deleteOrderDetail(Long id);
}

