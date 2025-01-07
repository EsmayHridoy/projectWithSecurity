package com.esmay.projectWithSecurity.Repository;

import com.esmay.projectWithSecurity.Models.Books;

import java.util.List;

public interface BookRepo {
    public boolean insertBook(Books book);
    public boolean findBook(int id);
    public boolean UpdateBookCount(int id,int BookCount);
    public boolean UpdateBookPrice(int id,float BookPrice);
    public int bookCount(int id);
    public boolean bookDelete(int id);
    public List<Books> findAllBooks();
}
