package poly.edu.Assignment.Service;
import java.util.List;

import poly.edu.Assignment.model.Order;
import poly.edu.Assignment.model.OrderDetail;

public interface OrderService {
    Order saveOrder(Order order, List<OrderDetail> orderDetails);
    Order getOrderById(Long id);
    List<Order> getAllOrders();
    void deleteOrder(Long id);
    Order updateStatus(Long orderId,String status);
    List<Order>getOrdersByUser(int id);
    List<Order> findByStatus(String status);
    Double sumTotalAmountWhereStatusLike(String status);
    Double sumTotalAmountForCurrentMonth(String status);
    Double getAverageOrderValue(String status);
    Long countOrdersThisMonth(String status);
    Long getCountOrder(String status);

}

