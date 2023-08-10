package com.slip.user.service;


import com.slip.user.Models.User;
import com.slip.user.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


public interface UserService {
    public List<User> getAllUsers();

    public User addUsers(User user);

    User getUserByHandle(String handle);
}
