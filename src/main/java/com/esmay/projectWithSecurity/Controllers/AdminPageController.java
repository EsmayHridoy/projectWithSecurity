package com.esmay.projectWithSecurity.Controllers;


import com.esmay.projectWithSecurity.Models.Books;
import com.esmay.projectWithSecurity.Models.User;
import com.esmay.projectWithSecurity.Services.BookService;
import com.esmay.projectWithSecurity.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AdminPageController {

    @Autowired
    BookService bookService;
    @Autowired
    UserService userService;


    @GetMapping("/addBookPage")
    public String redirAddBookPage(){
        return "addBookPage";
    }

    @PostMapping("/addBook")
    public String addBook(@RequestParam("bookId")int bookId,
                          @RequestParam("bookName") String bookName,
                          @RequestParam("bookAuthor") String bookAuthor,
                          @RequestParam("bookPrice") float bookPrice,
                          @RequestParam("bookCount") int bookCount){
        Books book = new Books(bookId,bookName,bookAuthor,bookPrice,bookCount);
        boolean flag=bookService.addBookDB(book);
        if(!flag)return "operationError";
        return "adminProfile";
    }

    @RequestMapping("/unauthorizedException")
    public String unauthorized() {
        return "unauthorizedException";
    }

    @GetMapping("/userList")
    public String getAllUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "userList";
    }


    @PostMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return "redirect:/userList";
    }
}
