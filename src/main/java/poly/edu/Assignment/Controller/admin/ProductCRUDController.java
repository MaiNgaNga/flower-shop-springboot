package poly.edu.Assignment.Controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import poly.edu.Assignment.Service.CategoryService;
import poly.edu.Assignment.Service.ColorService;
import poly.edu.Assignment.Service.ProductCategoryService;
import poly.edu.Assignment.Service.ProductService;
import poly.edu.Assignment.model.Category;
import poly.edu.Assignment.model.Color;
import poly.edu.Assignment.model.Product;
import poly.edu.Assignment.model.ProductCategory;

@Controller
@RequestMapping("/Product")
public class ProductCRUDController {

    @Autowired
    private ProductService productService;
    @Autowired 
    private ColorService colorService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductCategoryService productCategoryService;

    @ModelAttribute("products")
    public List<Product> getAllProducts() {
        return productService.findAll();
    }

    @ModelAttribute("colors")
    public List<Color> getAllColors() {
        return colorService.findAll();
    }

    @ModelAttribute("categories")
    public List<Category> getAllCategorys() {
        return categoryService.findAll();
    }
    
    @ModelAttribute("productCategories")
    public List<ProductCategory> getAllProductCategories() {
        return productCategoryService.findAll();
    }
    @RequestMapping("/index")
    public String index(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("view", "admin/ProductCRUD");
        return "admin/layout";
    }

    @PostMapping("/create")
    public String create(Model model, 
                         @Valid @ModelAttribute("product") Product product, Errors errors,
                         @RequestParam("imageFile") MultipartFile imageFile,
                         RedirectAttributes redirectAttributes) {
        if (errors.hasErrors()) {
            model.addAttribute("view", "admin/ProductCRUD");
            return "admin/layout";
        }
        try {
            productService.create(product, imageFile);
            redirectAttributes.addFlashAttribute("success", "Thêm sản phẩm thành công!");
            return "redirect:/Product/index";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("view", "admin/ProductCRUD");
            return "admin/layout";
        }
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") long id) {
        Product product = productService.findByID(id);
        
        model.addAttribute("product", product);
        model.addAttribute("view", "admin/ProductCRUD");
        return "admin/layout";
    }

    @PostMapping("/update")
    public String update(Model model,  @Valid @ModelAttribute("product") Product product,Errors errors,
                         @RequestParam(value="imageFile", required = false) MultipartFile imageFile ,     @RequestParam(value = "oldImage", required = false) String oldImage,
                          RedirectAttributes redirectAttributes) {
        if (errors.hasErrors()) {
            model.addAttribute("view", "admin/ProductCRUD");
            return "admin/layout";
        }
        try {
          
            productService.update(product, imageFile,oldImage);
            redirectAttributes.addFlashAttribute("success", "Cập nhật sản phẩm thành công!");
            return "redirect:/Product/edit/" + product.getId();
           
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("view", "admin/ProductCRUD");
            return "admin/layout";
        }
    }

    @RequestMapping("/delete/{id}")
    public String delete(Model model, 
    @ModelAttribute("product") Product product,Errors errors,
    @PathVariable("id") long id, RedirectAttributes redirectAttributes) {
        try {
            productService.deleteById(id);
            redirectAttributes.addFlashAttribute("success", "Đã xóa sản phẩm!");
            return "redirect:/Product/index";

        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/Product/edit/" + product.getId();

        }
    }
}
