package com.example.library.controller;

import com.example.library.model.User;
import com.example.library.service.BorrowService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BorrowUIController {

    private final BorrowService service;

    public BorrowUIController(BorrowService service) {
        this.service = service;
    }

    @GetMapping("/borrow-ui")
    public String getRequests(Model model, HttpSession session) {

        User user = (User) session.getAttribute("user");

        // 🔐 ADMIN ONLY
        if (!user.getRole().equals("ADMIN")) {
            return "redirect:/";
        }

        model.addAttribute("requests", service.getAllRequests());
        return "borrow";
    }

    @PostMapping("/borrow-ui/approve/{id}")
    public String approve(@PathVariable Long id, HttpSession session) {

        User user = (User) session.getAttribute("user");

        if (!user.getRole().equals("ADMIN")) {
            throw new RuntimeException("Access denied");
        }

        service.approve(id);
        return "redirect:/borrow-ui";
    }

    @PostMapping("/borrow-ui/reject/{id}")
    public String reject(@PathVariable Long id, HttpSession session) {

        User user = (User) session.getAttribute("user");

        if (!user.getRole().equals("ADMIN")) {
            throw new RuntimeException("Access denied");
        }

        service.reject(id);
        return "redirect:/borrow-ui";
    }
}