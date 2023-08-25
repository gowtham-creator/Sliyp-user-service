package com.slip.user.service.impl;

import com.slip.user.Models.Post;
import com.slip.user.Models.User;
import com.slip.user.repositories.UserRepository;
import com.slip.user.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

   @Override
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    @Override
    public User addUsers(User user){
        return userRepository.save(user);
    }
    @Override
    public User getUserByHandle(String handle){
        return userRepository.getUserByHandle(handle);
    }

}
