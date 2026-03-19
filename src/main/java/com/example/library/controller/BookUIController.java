package com.example.library.controller;

import com.example.library.model.Book;
import com.example.library.model.User;
import com.example.library.service.BookService;
import com.example.library.service.BorrowService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BookUIController {

    private final BookService service;
    private final BorrowService borrowService;

    public BookUIController(BookService service, BorrowService borrowService) {
        this.service = service;
        this.borrowService = borrowService;
    }

    @GetMapping("/books-ui")
    public String getBooks(Model model, HttpSession session) {

        User user = (User) session.getAttribute("user");

        model.addAttribute("books", service.getAllBooks());
        model.addAttribute("user", user);

        return "books";
    }

    @PostMapping("/books-ui/add")
    public String addBook(@RequestParam String title,
                          @RequestParam String author,
                          HttpSession session) {

        User user = (User) session.getAttribute("user");

        // 🔐 ADMIN CHECK
        if (!user.getRole().equals("ADMIN")) {
            throw new RuntimeException("Access denied");
        }

        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);

        service.addBook(book);

        return "redirect:/books-ui";
    }

    @PostMapping("/books-ui/borrow/{bookId}")
    public String borrowBook(@PathVariable Long bookId,
                             @RequestParam String userEmail,
                             HttpSession session) {

        User user = (User) session.getAttribute("user");

        // 🔐 USER CHECK
        if (!user.getRole().equals("USER")) {
            throw new RuntimeException("Only users can borrow");
        }

        borrowService.requestBook(userEmail, bookId);

        return "redirect:/books-ui";
    }
}