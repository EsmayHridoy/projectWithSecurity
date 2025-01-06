package com.esmay.projectWithSecurity.Repository;


import com.esmay.projectWithSecurity.Connect.MysqlConnect;
import com.esmay.projectWithSecurity.Models.User;
import org.springframework.stereotype.Component;

import javax.swing.plaf.nimbus.State;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

}
