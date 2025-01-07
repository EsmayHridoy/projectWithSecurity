package com.esmay.projectWithSecurity.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Controller
public class DashBoardController {



    @Autowired
    private DataSource dataSource;


    @GetMapping("/profile")
    public String viewProfile(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        String email = userDetails.getUsername(); // Getting the email from the authenticated user

        try (Connection connection = dataSource.getConnection()) {
            String query = "SELECT name, email, address, role FROM user_table WHERE email = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, email);

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        String name = resultSet.getString("name");
                        String address = resultSet.getString("address");
                        String role = resultSet.getString("role");

                        // Add the user data to the model
                        model.addAttribute("name", name);
                        model.addAttribute("email", email);
                        model.addAttribute("address", address);
                        model.addAttribute("role", role);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "profile";  // Return the profile view
    }
}
