package poly.edu.Assignment.utils;

import java.util.List;

import poly.edu.Assignment.model.User;

public interface AuthService {
    User getUser();

    List<String> getRole();

    boolean isAuthenticated();

    boolean hasAnyRoles(String... roles);

    boolean login(String email, String password);

    void logout();

    User getCurrentUser();



}
