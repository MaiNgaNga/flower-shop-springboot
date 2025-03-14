package poly.edu.Assignment.utils.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import poly.edu.Assignment.utils.CookieService;



@Service
public class CookieServiceImpl implements CookieService {
  @Autowired
  HttpServletRequest request;
  @Autowired
  HttpServletResponse response;

  @Override
  public Cookie create (String name, String value, int hours) {
    Cookie[] cookies = request.getCookies();
    
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(name)) {
                cookie.setValue(value);
                cookie.setMaxAge(hours * 3600);
                response.addCookie(cookie);
                return cookie;
            }
        }
    }

    Cookie newCookie = new Cookie(name, value);
    newCookie.setMaxAge(hours * 3600);
    response.addCookie(newCookie);
    return newCookie;
}
  @Override
  public Cookie get (String name){
    Cookie[] cookies = request.getCookies();
    if(cookies==null){
        return null;
    }
    for(Cookie cookie: cookies){
        if(cookie.getName().equals(name)){
            return cookie;
        }
    }
    return null;
  }
  @Override
  public String getValue(String name){
    Cookie[] cookies = request.getCookies();
    if(cookies== null){
        return null;
    }for(Cookie cookie: cookies){
        if(cookie.getName().equals(name)){
           return cookie.getValue();
        }
    }
    return null;
  }
  
  @Override

  public void delete(String name ){
    Cookie[] cookies= request.getCookies();
    if(cookies!=null){
    for(Cookie cookie: cookies){
        if(cookie.getName().equals(name)){
            cookie.setValue("");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
    }}
  }
}