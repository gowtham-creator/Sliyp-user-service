package com.slip.user.service;


import com.slip.user.Models.User;
import java.util.List;



public interface UserService {
    public List<User> getAllUsers();

    public User addUsers(User user);

    User getUserByHandle(String handle);

    User getUserById(Long id);

    User getUserByEmail(String email);
}
