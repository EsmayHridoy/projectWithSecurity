package com.esmay.projectWithSecurity.Services;


import com.esmay.projectWithSecurity.Models.Books;
import org.springframework.stereotype.Service;

@Service
public interface BookService {

    public boolean addBookDB(Books books);

}
