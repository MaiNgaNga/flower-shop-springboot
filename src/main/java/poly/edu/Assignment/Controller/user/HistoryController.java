package poly.edu.Assignment.Controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import poly.edu.Assignment.Service.OrderDetailService;
import poly.edu.Assignment.Service.OrderService;
import poly.edu.Assignment.dao.ProductCategoryDAO;
import poly.edu.Assignment.model.ProductCategory;
import poly.edu.Assignment.model.User;
import poly.edu.Assignment.utils.AuthService;



@Controller
public class HistoryController {
    @Autowired
    ProductCategoryDAO pro_ca_dao;

    @Autowired
    OrderService orderService;

    @Autowired
    AuthService authService;

    @Autowired
    OrderDetailService orderDetailService;

    @GetMapping("/history")
    public String getHistory(Model model) {
        
        return showForm(model);
    }
    @GetMapping("/history/delete/{orderId}")
    public String deleteOrder(Model model, @PathVariable("orderId") Long orderId) {
        orderService.updateStatus(orderId,"Đã hủy");
        model.addAttribute("message", "Đã hủy đơn hàng");
        return showForm(model);
    }
    
    public String showForm(Model model) {
        User user= authService.getUser();
        model.addAttribute("orders", orderService.getOrdersByUser(user.getId()));
        List<ProductCategory> productCategories=pro_ca_dao.findAll();
        model.addAttribute("productCategories", productCategories);
        model.addAttribute("view", "history");

        return "layouts/layout";
    }

}
