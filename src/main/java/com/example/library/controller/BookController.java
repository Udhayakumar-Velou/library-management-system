package com.example.library.controller;

import com.example.library.model.Book;
import com.example.library.service.BookService;
import com.example.library.dto.BookDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    // ✅ ADD BOOK
    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return service.addBook(book);
    }

    // ✅ GET ALL BOOKS
    @GetMapping
    public List<BookDTO> getBooks() {
        return service.getAllBooks();
    }

    // 🔍 SEARCH BOOKS
    @GetMapping("/search")
    public List<BookDTO> searchBooks(@RequestParam String title) {
        return service.searchBooks(title);
    }

    // ✅ DELETE
    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable Long id) {
        service.deleteBook(id);
        return "Book deleted";
    }

    // ✅ UPDATE
    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book book) {
        return service.updateBook(id, book);
    }
}