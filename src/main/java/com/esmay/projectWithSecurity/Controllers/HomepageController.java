package com.esmay.projectWithSecurity.Controllers;


import com.esmay.projectWithSecurity.Models.Books;
import com.esmay.projectWithSecurity.Services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomepageController {


    @Autowired
    BookService bookService;

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
        return "dashboard";
    }

    @GetMapping("/bookList")
    public String showBookList(Model model){
        List<Books> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "bookList";
    }
}
