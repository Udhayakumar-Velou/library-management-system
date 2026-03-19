package com.example.library.controller;

import com.example.library.model.BorrowRequest;
import com.example.library.service.BorrowService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/borrow")
public class BorrowController {

    private final BorrowService service;

    public BorrowController(BorrowService service) {
        this.service = service;
    }

    // ✅ USER REQUEST
    @PostMapping("/request")
    public BorrowRequest request(
            @RequestParam String email,
            @RequestParam Long bookId) {
        return service.requestBook(email, bookId);
    }

    // ✅ ADMIN VIEW
    @GetMapping
    public List<BorrowRequest> getAll() {
        return service.getAllRequests();
    }

    // ✅ ADMIN APPROVE
    @PutMapping("/approve/{id}")
    public BorrowRequest approve(@PathVariable Long id) {
        return service.approve(id);
    }

    // ✅ ADMIN REJECT
    @PutMapping("/reject/{id}")
    public BorrowRequest reject(@PathVariable Long id) {
        return service.reject(id);
    }
}