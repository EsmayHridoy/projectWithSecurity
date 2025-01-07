package com.esmay.projectWithSecurity.Services;

import com.esmay.projectWithSecurity.Models.User;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface UserService {
    public boolean addUser(User user);
    public List<User> getAllUsers();
    public void deleteUser(int id);
}
