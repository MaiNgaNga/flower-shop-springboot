package poly.edu.Assignment.Controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import poly.edu.Assignment.Service.OrderService;
import poly.edu.Assignment.Service.ProductService;
import poly.edu.Assignment.Service.UserService;


@Controller

public class Dashboard {
    @Autowired OrderService orderService;
    @Autowired UserService userService;
    @Autowired ProductService productService;
    @GetMapping("/dashboard")
    public String index(Model model) {
        String status="Đã giao";
        model.addAttribute("total", orderService.sumTotalAmountWhereStatusLike(status));
        model.addAttribute("totalCurrentMonth", orderService.sumTotalAmountForCurrentMonth(status));
        model.addAttribute("countUser", userService.findAll().size());
        model.addAttribute("avgOrder", orderService.getAverageOrderValue(status));
        model.addAttribute("countOrder", orderService.getCountOrder(status));
        model.addAttribute("countOrderCurrentMonth", orderService.countOrdersThisMonth(status));
        model.addAttribute("view", "admin/dashboard");
        return "admin/layout";
    }
    

}
