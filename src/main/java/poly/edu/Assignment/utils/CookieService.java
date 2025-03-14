package poly.edu.Assignment.utils;

import jakarta.servlet.http.Cookie;

public interface CookieService {
     Cookie create (String name, String value, int expiry);
     Cookie get(String name);
     String getValue(String name);
     void delete (String name);
}
