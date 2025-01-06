package com.esmay.projectWithSecurity.Services;

import com.esmay.projectWithSecurity.Models.User;
import org.springframework.stereotype.Service;


@Service
public interface UserService {
    public boolean addUser(User user);
}
