package poly.edu.Assignment.Service;

import java.util.List;

import poly.edu.Assignment.model.Category;

public interface CategoryService {
   List<Category> findAll();
   Category findByID(int id);
   Category create (Category entity);
   void update (Category entity);
   void deleteById(int id);
   boolean existsById(int id);
   
}
