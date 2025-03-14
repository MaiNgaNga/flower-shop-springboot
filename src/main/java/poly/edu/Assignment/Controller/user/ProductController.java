package poly.edu.Assignment.Controller.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import poly.edu.Assignment.Service.CategoryService;
import poly.edu.Assignment.Service.ProductCategoryService;
import poly.edu.Assignment.Service.ProductService;
import poly.edu.Assignment.model.Product;

@Controller
public class ProductController {
    @Autowired
    private ProductCategoryService pro_ca_service;

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService ca_Service;

    @RequestMapping("/ProductUser")
    public String index(Model model,
        @RequestParam("id") Integer pro_categoryId, 
        @RequestParam(name = "categoryId", required = false) Integer ca_Id,
        @RequestParam(name = "color", required = false) String color,
        @RequestParam(name = "min", required = false) Double minPrice,
        @RequestParam(name = "max", required = false) Double maxPrice,
        @RequestParam("p") Optional<Integer> p,
        @RequestParam(name = "filter", required = false) String filterType 
    ) { 
        Pageable pageable = PageRequest.of(p.orElse(0), 12);
        Page<Product> products = null;

        // Xác định kiểu lọc
        if ("price".equals(filterType) && minPrice != null && maxPrice != null) {
            products = productService.findByPriceRange(pro_categoryId, minPrice, maxPrice, pageable);
        } else if ("color".equals(filterType) && color != null) {
            products = productService.findByColor(pro_categoryId, color, pageable);
        } else if ("ca_id".equals(filterType) && ca_Id != null) {
            products = productService.findByCaId(pro_categoryId, ca_Id, pageable);
        } else {
            products = productService.findByProductCategoryIdPage(pro_categoryId, pageable);
        }

        model.addAttribute("page", products);
        model.addAttribute("pro_ca", pro_ca_service.findByID(pro_categoryId));
        model.addAttribute("productCategories", pro_ca_service.findAll());
        model.addAttribute("categogies", ca_Service.findAll());
        model.addAttribute("view", "product");

        return "layouts/layout";

    }
}
