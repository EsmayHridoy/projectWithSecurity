package com.esmay.projectWithSecurity.Repository;


import com.esmay.projectWithSecurity.Models.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserRepo {
    public boolean insertMember(User user);
    public boolean isExist(String mail);
    public List<User> findAllUsers();
    public void deleteUser(int id);
}
