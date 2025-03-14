package poly.edu.Assignment.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import poly.edu.Assignment.model.User;


public interface UserDAO extends JpaRepository<User,Integer> {
    boolean existsByEmail(String email);
    User findByEmail(String email);
    

}
