package com.esmay.projectWithSecurity.Services;


import com.esmay.projectWithSecurity.Models.Books;
import com.esmay.projectWithSecurity.Repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService{
    @Autowired
    BookRepo bookRepo;
    public boolean addBookDB(Books books){
        if(bookRepo.findBook(books.getBookId())){
            return bookRepo.UpdateBookCount
                    (books.getBookId(),books.getBookCount())
                    && bookRepo.UpdateBookPrice
                    (books.getBookId(),books.getBookPrice());
        }
        return bookRepo.insertBook(books);
    }

}
