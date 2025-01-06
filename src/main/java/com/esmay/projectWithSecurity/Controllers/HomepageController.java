package com.esmay.projectWithSecurity.Controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomepageController {

    @GetMapping("/home")
    public String home(){
        return "Homepage";
    }

    @GetMapping("/loginpage")
    public String callLogin(){
        return "LoginPage";
    }

    @GetMapping("/registration")
    public String callRegistration(){
        return "RegistrationPage";
    }
    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard"; // Renders dashboard after login
    }
}
