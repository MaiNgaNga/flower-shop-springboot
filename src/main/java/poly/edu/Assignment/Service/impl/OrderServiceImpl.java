package poly.edu.Assignment.Service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import poly.edu.Assignment.Service.OrderService;
import poly.edu.Assignment.dao.OrderDAO;
import poly.edu.Assignment.dao.OrderDetailDAO;
import poly.edu.Assignment.model.Order;
import poly.edu.Assignment.model.OrderDetail;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDAO dao;
    @Autowired
    private OrderDetailDAO orderDetailDAO;

    @Override
    public Order saveOrder(Order order, List<OrderDetail> orderDetails) {
        Order savedOrder= dao.save(order);
        for (OrderDetail detail : orderDetails) {
            detail.setOrder(savedOrder);
            orderDetailDAO.save(detail);
        }

        return savedOrder;
    }


    @Override
    public Order getOrderById(Long id) {
        return dao.findById(id).orElse(null);
    }
    @Override
    public Order updateStatus(Long orderId,String status){
        Order order =getOrderById(orderId);
        order.setStatus(status);
        return dao.save(order);
    } 

    @Override
    public List<Order> getAllOrders() {
        return dao.findAll();
    }

    @Override
    @Transactional
    public void deleteOrder(Long id) {
        dao.deleteById(id);
    }

    @Override
    public List<Order> getOrdersByUser(int userid) {
        return dao.findByUserIdOrderByIdDesc(userid);
    }
    @Override
    public List<Order> findByStatus(String status){
        return dao.findByStatusOrderByIdDesc(status);
    }
    @Override
    public Double sumTotalAmountWhereStatusLike(String status){
        return dao.sumTotalAmount(status);
    }
    @Override
    public Double sumTotalAmountForCurrentMonth(String status){
        return dao.sumTotalAmountForCurrentMonth(status);
    }
    @Override
    public Double getAverageOrderValue(String status){
        return dao.getAverageOrderValue(status);
    }
    @Override
    public Long countOrdersThisMonth(String status){
        return dao.countOrdersThisMonth(status);
    }
    @Override
    public   Long getCountOrder(String status){
        return  dao.getCountOrder(status);
    }

}
