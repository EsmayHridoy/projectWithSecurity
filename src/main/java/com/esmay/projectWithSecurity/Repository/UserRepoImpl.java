package com.esmay.projectWithSecurity.Repository;


import com.esmay.projectWithSecurity.Connect.MysqlConnect;
import com.esmay.projectWithSecurity.Models.User;
import org.springframework.stereotype.Component;

import javax.swing.plaf.nimbus.State;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserRepoImpl implements UserRepo {

    public boolean insertMember(User user){
        MysqlConnect mysqlConnect = new MysqlConnect();
        boolean flag = false;
        String QUERY = "INSERT INTO user_table " +
                "(name, password, email, address, role) " +
                "VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement Statement = mysqlConnect.connect().prepareStatement(QUERY)) {
            Statement.setString(1,user.getName());
            Statement.setString(2,user.getPassword());
            Statement.setString(3,user.getEmail());
            Statement.setString(4,user.getAddress());
            Statement.setString(5,user.getRole());
            int rowsAffected = Statement.executeUpdate();
            if (rowsAffected > 0) {
                flag = true;

            } else {
                System.out.println("Failed to insert user.");
            }
        } catch (SQLException e) {
            System.out.println("Error while inserting user: " + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public boolean isExist(String mail) {
        MysqlConnect mysqlConnect = new MysqlConnect();
        boolean flag = false;
        String SQLQ = "SELECT * FROM user_table WHERE email = ?";
        try (PreparedStatement Statement = mysqlConnect.connect().prepareStatement(SQLQ)) {
            Statement.setString(1, mail);
            ResultSet resultSet = Statement.executeQuery();
            while (resultSet.next()) {
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public List<User> findAllUsers() {
        MysqlConnect mysqlConnect = new MysqlConnect();
        List<User> users = new ArrayList<>();
        String SQLQ = "SELECT * FROM user_table";
        try (PreparedStatement Statement = mysqlConnect.connect().prepareStatement(SQLQ)) {
            ResultSet resultSet = Statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
                user.setAddress(resultSet.getString("address"));
                user.setRole(resultSet.getString("role"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void deleteUser(int id) {
        MysqlConnect mysqlConnect = new MysqlConnect();
        String DELETE_QUERY = "DELETE FROM user_table WHERE id = ?";
        try (PreparedStatement statement = mysqlConnect.connect().prepareStatement(DELETE_QUERY)) {
            statement.setInt(1, id);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("User with ID " + id + " deleted successfully.");
            } else {
                System.out.println("User with ID " + id + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
