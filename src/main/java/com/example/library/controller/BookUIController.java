package com.example.library.controller;

import com.example.library.model.Book;
import com.example.library.model.User;
import com.example.library.service.BookService;
import com.example.library.service.BorrowService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BookUIController {

    private final BookService service;
    private final BorrowService borrowService;

    public BookUIController(BookService service, BorrowService borrowService) {
        this.service = service;
        this.borrowService = borrowService;
    }

    // ✅ SHOW BOOKS
    @GetMapping("/books-ui")
    public String getBooks(@RequestParam(required = false) String keyword,
                           Model model,
                           HttpSession session) {

        List<?> books;

        if (keyword != null && !keyword.isBlank()) {
            books = service.searchBooks(keyword);
        } else {
            books = service.getAllBooks();
        }

        model.addAttribute("books", books);

        User user = (User) session.getAttribute("user");

        if (user != null) {
            model.addAttribute("userEmail", user.getEmail());
            model.addAttribute("role", user.getRole());
        } else {
            return "redirect:/login-ui";
        }

        return "books";
    }

    // ✅ ADD BOOK (ADMIN ONLY)
    @PostMapping("/books-ui/add")
    public String addBook(@RequestParam String title,
                          @RequestParam String author,
                          HttpSession session) {

        User user = (User) session.getAttribute("user");

        if (user == null || !user.getRole().equals("ADMIN")) {
            return "redirect:/books-ui?error=unauthorized";
        }

        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);

        service.addBook(book);

        return "redirect:/books-ui";
    }

    // ✅ BORROW (USER ONLY)
    @PostMapping("/books-ui/borrow/{id}")
    public String borrow(@PathVariable Long id,
                         HttpSession session) {

        User user = (User) session.getAttribute("user");

        if (user == null || !user.getRole().equals("USER")) {
            return "redirect:/books-ui?error=unauthorized";
        }

        borrowService.requestBook(user.getEmail(), id);

        return "redirect:/books-ui";
    }

    // ✅ DELETE (ADMIN ONLY)
    @PostMapping("/books-ui/delete/{id}")
    public String delete(@PathVariable Long id,
                         HttpSession session) {

        User user = (User) session.getAttribute("user");

        if (user == null || !user.getRole().equals("ADMIN")) {
            return "redirect:/books-ui?error=unauthorized";
        }

        service.deleteBook(id);

        return "redirect:/books-ui";
    }

    // ✅ UPDATE (ADMIN ONLY)
    @PostMapping("/books-ui/update/{id}")
    public String update(@PathVariable Long id,
                         @RequestParam String title,
                         @RequestParam String author,
                         HttpSession session) {

        User user = (User) session.getAttribute("user");

        if (user == null || !user.getRole().equals("ADMIN")) {
            return "redirect:/books-ui?error=unauthorized";
        }

        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);

        service.updateBook(id, book);

        return "redirect:/books-ui";
    }
}