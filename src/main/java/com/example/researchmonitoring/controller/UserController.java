// src/main/java/com/example/researchmonitoring/controller/UserController.java
package com.example.researchmonitoring.controller;

import com.example.researchmonitoring.dto.LoginDto;
import com.example.researchmonitoring.dto.UserDto;
import com.example.researchmonitoring.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService users;

    public UserController(UserService users) {
        this.users = users;
    }

    /** регистрация */
    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@Valid @RequestBody UserDto dto) {
        return ResponseEntity.status(201).body(users.register(dto));
    }

    /** логин → JWT */
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@Valid @RequestBody LoginDto dto) {
        String token = users.login(dto.getUsername(), dto.getPassword());
        return ResponseEntity.ok(Map.of("token", token));
    }
}
