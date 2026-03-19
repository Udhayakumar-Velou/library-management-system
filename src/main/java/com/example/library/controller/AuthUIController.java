package com.example.library.controller;

import com.example.library.model.User;
import com.example.library.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthUIController {

    private final UserService service;

    public AuthUIController(UserService service) {
        this.service = service;
    }

    // LOGIN PAGE
    @GetMapping("/login-ui")
    public String loginPage() {
        return "login";
    }

    // REGISTER PAGE
    @GetMapping("/register-ui")
    public String registerPage() {
        return "register";
    }

    // HANDLE REGISTER
    @PostMapping("/register-ui")
    public String register(@RequestParam String email,
                           @RequestParam String password,
                           @RequestParam String role) {

        service.register(email, password, role);

        return "redirect:/login-ui";
    }

    // HANDLE LOGIN
    @PostMapping("/login-ui")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        HttpSession session) {

        User user = service.login(email, password);

        session.setAttribute("user", user);

        return "redirect:/";
    }

    // LOGOUT
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login-ui";
    }
}