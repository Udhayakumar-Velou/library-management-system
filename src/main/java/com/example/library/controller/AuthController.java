package com.example.library.controller;

import com.example.library.model.User;
import com.example.library.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController {

    private final UserService service;

    public AuthController(UserService service) {
        this.service = service;
    }

    // ✅ SHOW LOGIN PAGE (THIS FIXES YOUR ERROR)
    @GetMapping("/login-ui")
    public String loginPage() {
        return "login"; // login.html
    }

    // ✅ LOGIN
    @PostMapping("/login-ui")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        HttpSession session) {

        User user = service.login(email, password);

        if (user == null) {
            return "redirect:/login-ui?error";
        }

        session.setAttribute("user", user);

        return "redirect:/books-ui";
    }

    // ✅ LOGOUT
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login-ui";
    }

    // SHOW REGISTER PAGE
    @GetMapping("/register-ui")
    public String registerPage() {
        return "register";
    }

    // HANDLE REGISTER
    @PostMapping("/register-ui")
    public String register(@RequestParam String email,
                           @RequestParam String password) {

        service.register(email, password, "USER"); // ✅ FORCE USER

        return "redirect:/login-ui";
    }
}