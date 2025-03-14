package com.example.library.repository;

import com.example.library.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    
    // Find transactions by user ID
    List<Transaction> findByUserId(Long userId);
    
    // Find active (borrowed but not returned) transactions by user ID
    List<Transaction> findByUserIdAndStatus(Long userId, String status);
}
