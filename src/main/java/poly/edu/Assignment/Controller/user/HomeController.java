package poly.edu.Assignment.Controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import poly.edu.Assignment.Service.ProductCategoryService;
import poly.edu.Assignment.Service.ProductService;

import poly.edu.Assignment.model.Product;


@Controller
public class HomeController {
    @Autowired
    ProductCategoryService pro_ca_Service;
    @Autowired
    ProductService pro_Service;
    @GetMapping("/home")
    public String home(Model model){

        model.addAttribute("productCategories", pro_ca_Service.findAll());

        List<Product> lastProducts=pro_Service.findLatestProductsPerCategory();
        model.addAttribute("lastProducts", lastProducts);

        model.addAttribute("favProducts", pro_Service.findBestSellingProductPerCategory());
      
        model.addAttribute("softByQuantitys",pro_Service.findByProductOrderByQuantityDesc());

        List<Product> listBoHoaTuoi=pro_Service.findProductByNameProductCategory("Bó hoa tươi");
        model.addAttribute("listBoHoaTuoi", listBoHoaTuoi);

        List<Product> listGioHoaTuoi=pro_Service.findProductByNameProductCategory("Giỏ hoa tươi");
        model.addAttribute("listGioHoaTuoi", listGioHoaTuoi);

        List<Product> listHoaChiaBuon=pro_Service.findProductByNameProductCategory("Hoa chia buồn");
        model.addAttribute("listHoaChiaBuon", listHoaChiaBuon);

        List<Product> listHoaKhaiTruong=pro_Service.findProductByNameProductCategory("Hoa khai trương");
        model.addAttribute("listHoaKhaiTruong", listHoaKhaiTruong);

        model.addAttribute("view", "home");
        return "layouts/layout";
    }
}
