package com.example.library.service;

import com.example.library.model.BorrowRequest;
import com.example.library.repository.BorrowRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BorrowService {

    private static final Logger logger = LoggerFactory.getLogger(BorrowService.class);

    private final BorrowRepository repo;

    public BorrowService(BorrowRepository repo) {
        this.repo = repo;
    }

    // ✅ REQUEST BOOK
    public BorrowRequest requestBook(String email, Long bookId) {

        // 🔐 INPUT VALIDATION
        if (email == null || email.isBlank()) {
            throw new RuntimeException("Invalid user email");
        }

        if (bookId == null) {
            throw new RuntimeException("Invalid book ID");
        }

        logger.info("Borrow request created by user: {} for book: {}", email, bookId);

        BorrowRequest req = new BorrowRequest();
        req.setUserEmail(email);
        req.setBookId(bookId);
        req.setStatus("PENDING");

        return repo.save(req);
    }

    // ✅ GET ALL REQUESTS
    public List<BorrowRequest> getAllRequests() {
        logger.info("Fetching all borrow requests");
        return repo.findAll();
    }

    // ✅ APPROVE
    public BorrowRequest approve(Long id) {

        if (id == null) {
            throw new RuntimeException("Invalid request ID");
        }

        BorrowRequest req = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        req.setStatus("APPROVED");

        logger.info("Request approved: {}", id);

        return repo.save(req);
    }

    // ✅ REJECT
    public BorrowRequest reject(Long id) {

        if (id == null) {
            throw new RuntimeException("Invalid request ID");
        }

        BorrowRequest req = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        req.setStatus("REJECTED");

        logger.info("Request rejected: {}", id);

        return repo.save(req);
    }
}