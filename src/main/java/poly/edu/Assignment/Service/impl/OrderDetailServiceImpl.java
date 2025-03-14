package poly.edu.Assignment.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import poly.edu.Assignment.Service.OrderDetailService;
import poly.edu.Assignment.dao.OrderDetailDAO;
import poly.edu.Assignment.model.Order;
import poly.edu.Assignment.model.OrderDetail;

import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private OrderDetailDAO dao;

    public List<OrderDetail> getOrderDetailsByOrder(Order order) {
        return dao.findByOrder(order);}

    @Override
    public OrderDetail getOrderDetailById(Long id) {
        return dao.findById(id).orElse(null);
    }
   

    @Override
    public List<OrderDetail> getAllOrderDetails() {
        return dao.findAll();
    }

    @Override
    @Transactional
    public void deleteOrderDetail(Long orderId) {
        dao.deleteByOrderId(orderId);;
    }
}

