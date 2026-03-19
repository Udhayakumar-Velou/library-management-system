package com.example.library.service;

import com.example.library.model.BorrowRequest;
import com.example.library.repository.BorrowRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BorrowService {

    private final BorrowRepository repo;

    public BorrowService(BorrowRepository repo) {
        this.repo = repo;
    }

    // ✅ REQUEST BOOK
    public BorrowRequest requestBook(String email, Long bookId) {

        BorrowRequest req = new BorrowRequest();
        req.setUserEmail(email);
        req.setBookId(bookId);
        req.setStatus("PENDING");

        return repo.save(req);
    }

    // ✅ GET ALL REQUESTS
    public List<BorrowRequest> getAllRequests() {
        return repo.findAll();
    }

    // ✅ APPROVE
    public BorrowRequest approve(Long id) {
        BorrowRequest req = repo.findById(id).orElseThrow();
        req.setStatus("APPROVED");
        return repo.save(req);
    }

    // ✅ REJECT
    public BorrowRequest reject(Long id) {
        BorrowRequest req = repo.findById(id).orElseThrow();
        req.setStatus("REJECTED");
        return repo.save(req);
    }
}