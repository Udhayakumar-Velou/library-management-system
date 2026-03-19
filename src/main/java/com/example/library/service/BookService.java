package com.example.library.service;

import com.example.library.model.Book;
import com.example.library.repository.BookRepository;
import com.example.library.dto.BookDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository repo;

    public BookService(BookRepository repo) {
        this.repo = repo;
    }

    // ✅ ADD BOOK
    public Book addBook(Book book) {
        return repo.save(book);
    }

    // ✅ GET ALL BOOKS
    public List<BookDTO> getAllBooks() {
        return repo.findAll()
                .stream()
                .map(b -> new BookDTO(b.getId(), b.getTitle(), b.getAuthor()))
                .toList();
    }

    // 🔍 SEARCH BOOKS
    public List<BookDTO> searchBooks(String title) {
        return repo.findByTitleContainingIgnoreCase(title)
                .stream()
                .map(b -> new BookDTO(b.getId(), b.getTitle(), b.getAuthor()))
                .toList();
    }

    // ✅ DELETE BOOK
    public void deleteBook(Long id) {
        repo.deleteById(id);
    }

    // ✅ UPDATE BOOK
    public Book updateBook(Long id, Book newBook) {
        Book book = repo.findById(id).orElseThrow();

        book.setTitle(newBook.getTitle());
        book.setAuthor(newBook.getAuthor());

        return repo.save(book);
    }
}