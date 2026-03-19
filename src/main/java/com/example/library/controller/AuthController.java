package com.example.library.controller;

import com.example.library.model.User;
import com.example.library.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import jakarta.servlet.http.HttpSession;

// ✅ ADD THIS (IMPORTANT FIX)
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

@Controller
public class AuthController {

    private final UserService service;

    public AuthController(UserService service) {
        this.service = service;
    }

    // ✅ SHOW LOGIN PAGE
    @GetMapping("/login-ui")
    public String loginPage() {
        return "login";
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

    // ✅ SHOW REGISTER PAGE
    @GetMapping("/register-ui")
    public String registerPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    // ✅ REGISTER (VALIDATION WORKING)
    @PostMapping("/register-ui")
    public String register(@ModelAttribute @Valid User user,
                           BindingResult result) {

        if (result.hasErrors()) {
            return "register";
        }

        service.register(user.getEmail(), user.getPassword(), "USER");

        return "redirect:/login-ui";
    }
}