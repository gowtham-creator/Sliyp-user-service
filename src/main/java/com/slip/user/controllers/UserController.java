package com.slip.user.controllers;

import com.slip.user.Models.Tasks;
import com.slip.user.Models.User;
import com.slip.user.dto.LoginRequestDto;
import com.slip.user.dto.LoginResponseDto;
import com.slip.user.service.EmailService;
import com.slip.user.service.TasksService;
import com.slip.user.service.UserService;
import com.slip.user.util.JwtTokenUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;


    public UserController(UserService userService, PasswordEncoder passwordEncoder, EmailService emailService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/profilee")
    public ResponseEntity<User> getLoggedInUser(){
        return ResponseEntity.ok(userService.getUserById(Long.valueOf("22")));
    }


    @PostMapping("/register")
    public ResponseEntity<User> addUsers(@RequestBody User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return ResponseEntity.ok(userService.addUsers(user));
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequestDto) {
        User user = userService.getUserByEmail(loginRequestDto.getEmail());

        // Check if the user exists and if the password matches
        if (passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())) {
            System.out.println(user.getUsername());
            // Generate a JWT token
            String jwtToken = JwtTokenUtil.generateToken(user);

            emailService.sendEmail(loginRequestDto.getEmail(),"SLiYp login","Hii "+user.getName()+",\n You are logged-in SLiYp successfully");

            // Return the JWT token in the response
            return ResponseEntity.ok(LoginResponseDto.builder()
                            .token(jwtToken)
                            .user(user.getRef().toString())
                            .username(user.getName())
                            .status(true)
                            .msg("Login successful..")
                    .build());
        } else {
            // Invalid credentials
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
}

