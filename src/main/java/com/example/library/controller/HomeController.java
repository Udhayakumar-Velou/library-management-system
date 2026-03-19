package com.example.library.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(HttpSession session) {

        // ❌ if not logged in → go to login
        if (session.getAttribute("user") == null) {
            return "redirect:/login-ui";
        }

        return "index";
    }
}