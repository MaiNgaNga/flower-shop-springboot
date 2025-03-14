package poly.edu.Assignment.Service;

import java.util.List;

import poly.edu.Assignment.model.ProductCategory;


public interface ProductCategoryService {
   List<ProductCategory> findAll();
   ProductCategory findByID(int id);
   ProductCategory create (ProductCategory entity);
   void update (ProductCategory entity);
   void deleteById(int id);
   boolean existsById(int id);
   
}
