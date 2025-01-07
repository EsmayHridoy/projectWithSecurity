package com.esmay.projectWithSecurity.Services;


import com.esmay.projectWithSecurity.Models.Books;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {

    public boolean addBookDB(Books books);
    public List<Books> getAllBooks();
    public boolean buyBook(int id);
}
