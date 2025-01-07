package com.esmay.projectWithSecurity.Repository;


import com.esmay.projectWithSecurity.Connect.MysqlConnect;
import com.esmay.projectWithSecurity.Models.Books;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class BookRepoImpl implements BookRepo {

    public boolean insertBook(Books books){
        MysqlConnect mysqlConnect = new MysqlConnect();
        boolean flag = false;
        String QUERY = "INSERT INTO book_table " +
                "(book_id, book_name, book_author, book_price, book_count) " +
                "VALUES (?, ?, ?, ? , ?)";
        try (PreparedStatement Statement = mysqlConnect.connect().prepareStatement(QUERY)) {
            Statement.setInt(1,books.getBookId());
            Statement.setString(2,books.getBookName());
            Statement.setString(3,books.getBookAuthor());
            Statement.setFloat(4,books.getBookPrice());
            Statement.setInt(5,books.getBookCount());
            int rowsAffected = Statement.executeUpdate();
            if (rowsAffected > 0) {
                flag = true;

            } else {
                System.out.println("Failed to insert book.");
            }
        } catch (SQLException e) {
            System.out.println("Error while inserting book: " + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public boolean findBook(int id){
        MysqlConnect mysqlConnect = new MysqlConnect();
        boolean flag = false;

        String QUERY ="SELECT * FROM book_table " +
                "WHERE book_id = ?";

        try (PreparedStatement Statement = mysqlConnect.connect().prepareStatement(QUERY)) {
            Statement.setInt(1,id);

            ResultSet resultSet = Statement.executeQuery();
            while(resultSet.next()){
                flag=true;
                System.out.println("Found books");
            }

        } catch (SQLException e) {
            System.out.println("Error while inserting book: " + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public boolean UpdateBookCount(int id,int BookCount){
        MysqlConnect mysqlConnect = new MysqlConnect();
        boolean flag = false;
        String QUERY = "UPDATE book_table " +
                "SET book_count = book_count+ ? " +
                "WHERE book_id = ?";
        try (PreparedStatement Statement = mysqlConnect.connect().prepareStatement(QUERY)) {
            Statement.setInt(1,BookCount);
            Statement.setInt(2,id);
            int rowsAffected = Statement.executeUpdate();
            if (rowsAffected > 0) {
                flag = true;

            } else {
                System.out.println("Failed to insert book.");
            }
        } catch (SQLException e) {
            System.out.println("Error while inserting book: " + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public boolean UpdateBookPrice(int id,float BookPrice){
        MysqlConnect mysqlConnect = new MysqlConnect();
        boolean flag = false;
        String QUERY = "UPDATE book_table " +
                "SET book_price =  ? " +
                "WHERE book_id=?";
        try (PreparedStatement Statement = mysqlConnect.connect().prepareStatement(QUERY)) {
            Statement.setFloat(1,BookPrice);
            Statement.setInt(2,id);
            int rowsAffected = Statement.executeUpdate();
            if (rowsAffected > 0) {
                flag = true;

            } else {
                System.out.println("Failed to insert book.");
            }
        } catch (SQLException e) {
            System.out.println("Error while inserting book: " + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public int bookCount(int id){
        MysqlConnect mysqlConnect = new MysqlConnect();
        int bookCnt=0;

        String QUERY ="SELECT book_count FROM book_table " +
                "WHERE book_id = ?";

        try (PreparedStatement Statement = mysqlConnect.connect().prepareStatement(QUERY)) {
            Statement.setInt(1,id);

            ResultSet resultSet = Statement.executeQuery();
            while(resultSet.next()){
                bookCnt+=resultSet.getInt("book_count");

            }

        } catch (SQLException e) {
            System.out.println("Error while inserting book: " + e.getMessage());
            e.printStackTrace();
        }


        return bookCnt;

    }

    public boolean bookDelete(int id){
        MysqlConnect mysqlConnect = new MysqlConnect();
        boolean flag = false;
        String QUERY = "DELETE FROM book_table WHERE book_id = ?";
        try (PreparedStatement Statement = mysqlConnect.connect().prepareStatement(QUERY)) {

            Statement.setInt(1,id);
            int rowsAffected = Statement.executeUpdate();
            if (rowsAffected > 0) {
                flag = true;

            } else {
                System.out.println("Failed to insert book.");
            }
        } catch (SQLException e) {
            System.out.println("Error while inserting book: " + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public List<Books> findAllBooks() {
        MysqlConnect mysqlConnect = new MysqlConnect();
        List<Books> booksList = new ArrayList<>();
        String QUERY = "SELECT * FROM book_table"; // Fetching all books

        try (PreparedStatement statement = mysqlConnect.connect().prepareStatement(QUERY)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Books book = new Books();
                book.setBookId(resultSet.getInt("book_id"));
                book.setBookName(resultSet.getString("book_name"));
                book.setBookAuthor(resultSet.getString("book_author"));
                book.setBookPrice(resultSet.getFloat("book_price"));
                book.setBookCount(resultSet.getInt("book_count"));
                booksList.add(book);
            }
        } catch (SQLException e) {
            System.out.println("Error while retrieving books: " + e.getMessage());
            e.printStackTrace();
        }
        return booksList;
    }




}
