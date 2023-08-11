package com.slip.user.controllers;

import com.slip.user.Models.User;
import com.slip.user.dto.LoginRequestDto;
import com.slip.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;

    public UserController(UserService userService, PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping("/register")
    public ResponseEntity<User> addUsers(@RequestBody User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return ResponseEntity.ok(userService.addUsers(user));
    }
    @PostMapping("/login")
    public ResponseEntity<String> authenticateUser(
             @RequestBody LoginRequestDto loginRequest) {

        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmail());
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails.getUsername(),
                loginRequest.getPassword()

        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return ResponseEntity.ok("Login success");
    }
}
