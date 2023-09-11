package com.slip.user.controllers;

import com.slip.user.Models.Tasks;
import com.slip.user.Models.User;
import com.slip.user.dto.LoginRequestDto;
import com.slip.user.dto.LoginResponseDto;
import com.slip.user.service.TasksService;
import com.slip.user.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
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
    private final TasksService tasksService;


    public UserController(UserService userService, PasswordEncoder passwordEncoder, TasksService tasksService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.tasksService = tasksService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/profilee")
    public ResponseEntity<User> getLoggedInUser(){
        return ResponseEntity.ok(userService.getUserById(Long.valueOf("22")));
    }
    @GetMapping("/tasks")
    public Map<String,List<Tasks>> getTasksByUserID(@RequestParam String UserId){
        return Map.of("tasks",tasksService.findAllTasksByUserId(UserId));
    }
    @GetMapping("/tasks/{id}")
    public Tasks getTasksID(@PathVariable String id){
        return tasksService.findTaskByID(Long.valueOf(id));
    }
    @PostMapping("/tasks")
    public ResponseEntity<Tasks> addTasksForUser(@RequestParam String UserId ,@RequestBody Tasks tasks){
        tasks.setUserRef(UserId);
        return ResponseEntity.ok(tasksService.save(tasks));
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
            String jwtToken = generateJwtToken(user);


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

    private String generateJwtToken(User user) {
        // Define the claims for the JWT token (you can add more if needed)
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getRef());

        // Set the token expiration time (e.g., 1 hour from now)
        long tokenExpirationMillis = System.currentTimeMillis() + 3600 * 1000; // 1 hour

        SecureRandom random = new SecureRandom();
        byte[] keyBytes = new byte[64];
        random.nextBytes(keyBytes);

        // Encode the key in Base64
        String secretKey = Base64.getEncoder().encodeToString(keyBytes);

        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(tokenExpirationMillis))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }
}

