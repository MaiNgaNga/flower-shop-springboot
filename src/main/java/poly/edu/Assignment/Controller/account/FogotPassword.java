package poly.edu.Assignment.Controller.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import poly.edu.Assignment.Service.UserService;
import poly.edu.Assignment.model.Mail;
import poly.edu.Assignment.model.User;
import poly.edu.Assignment.utils.impl.SendMailService;


@Controller
public class FogotPassword {
    
    @Autowired
    private UserService userService;

    @Autowired
    private SendMailService sendMailService;

    @GetMapping("/forgotPassword")
    public String forgot(Model model){
        return forgotPath(model);
    }
    @PostMapping("/forgotPassword")
    public String forgot(@RequestParam String name, @RequestParam String email, Model model) {
        if(name.isEmpty()||email.isEmpty()){
            model.addAttribute("error", "Chưa nhập đầy đủ thông tin!");
            return forgotPath(model);
        }
        User user=userService.findByEmail(email);
        if(user==null){
            model.addAttribute("error", "Sai email đăng nhập");
            return forgotPath(model);
        }
        if(!user.getName().equalsIgnoreCase(name)){
            model.addAttribute("error", "Sai tên");
            return forgotPath(model);
        }else{
            sendMailService.send(new Mail(user.getEmail(),"Quên mật khẩu","Mật khẩu của bạn là: "+user.getPassword()));
            model.addAttribute("success", "Đã gửi mật khẩu đến email của bạn");
            return  forgotPath(model);
        }

    }

    public String forgotPath(Model model){
        model.addAttribute("view", "account/forgotPassword");
        return "account/layout";
    }
}
