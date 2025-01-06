package com.esmay.projectWithSecurity.Controllers;


import com.esmay.projectWithSecurity.Models.User;
import com.esmay.projectWithSecurity.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationController {


    @Autowired
    UserService userService;

    @PostMapping("/register")
    public String addUser(@RequestParam("name") String name,
                          @RequestParam("email") String email,
                          @RequestParam("password") String password,
                          @RequestParam("address") String address){

        User user = new User(1, name, password, email, address, "General");

        userService.addUser(user);

        return "LoginPage";
    }
}
