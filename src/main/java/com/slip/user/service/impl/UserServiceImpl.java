package com.slip.user.service.impl;

import com.slip.user.Models.User;
import com.slip.user.repositories.UserRepository;
import com.slip.user.service.EmailService;
import com.slip.user.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final EmailService emailService;
    public UserServiceImpl(UserRepository userRepository, EmailService emailService) {
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

   @Override
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    @Override
    public User addUsers(User user){
        if(!Objects.isNull(userRepository.getUserByEmail(user.getEmail()))){
        throw new RuntimeException("User already exists");
        }
        User userCreated= userRepository.save(user);
        emailService.sendEmail(userCreated.getEmail(),"SLiYp User Created","Hii "+user.getName()+",\n Your  SLiYp  account has created successfully");
        return userCreated;
    }
    @Override
    public User getUserByHandle(String handle){
        return userRepository.getUserByHandle(handle);
    }
    @Override
    public User getUserById(Long id){
        return userRepository.findById(id).orElseThrow();
    }
    @Override
    public User getUserByEmail(String email){
        return userRepository.getUserByEmail(email);
    }

}
