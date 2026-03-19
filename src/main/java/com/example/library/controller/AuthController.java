package com.example.library.controller;

import com.example.library.model.User;
import com.example.library.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    private final UserService service;

    public AuthController(UserService service) {
        this.service = service;
    }

    // ✅ REGISTER WITH ROLE
    @GetMapping("/register")
    public User register(@RequestParam String email,
                         @RequestParam String password,
                         @RequestParam(defaultValue = "USER") String role) {

        return service.register(email, password, role);
    }

    // ✅ LOGIN
    @GetMapping("/login")
    public User login(@RequestParam String email,
                      @RequestParam String password) {

        return service.login(email, password);
    }
}