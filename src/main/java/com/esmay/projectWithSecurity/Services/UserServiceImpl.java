package com.esmay.projectWithSecurity.Services;

import com.esmay.projectWithSecurity.Models.User;
import com.esmay.projectWithSecurity.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean addUser(User user) {
        if (userRepo.isExist(user.getEmail())) {
            return false;
        }
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepo.insertMember(user);
    }

    public List<User> getAllUsers() {
        return userRepo.findAllUsers();
    }

    public void deleteUser(int id) {
        userRepo.deleteUser(id);
    }
}

