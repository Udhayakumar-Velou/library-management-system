package com.example.library.repository;

import com.example.library.model.BorrowRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowRepository extends JpaRepository<BorrowRequest, Long> {
}