package com.esmay.projectWithSecurity.Controllers;


import com.esmay.projectWithSecurity.Models.Books;
import com.esmay.projectWithSecurity.Services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AdminPageController {

    @Autowired
    BookService bookService;

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
        if(!flag)return "successful";
        return "adminProfile";
    }

    @RequestMapping("/unauthorizedException")
    public String unauthorized() {
        return "unauthorizedException"; // This will map to the Thymeleaf template
    }





}
