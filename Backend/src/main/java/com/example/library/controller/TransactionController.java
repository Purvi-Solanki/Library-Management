package com.example.library.controller;

import com.example.library.model.Book;
import com.example.library.model.Transaction;
import com.example.library.model.User;
import com.example.library.repository.BookRepository;
import com.example.library.repository.TransactionRepository;
import com.example.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/transactions")
@CrossOrigin(origins = "http://localhost:3000") // ✅ Allow frontend requests
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    // ✅ Borrow Book
    @PostMapping("/borrow")
    public ResponseEntity<?> borrowBook(@RequestParam Long userId, @RequestParam Long bookId) {
        System.out.println("🔹 Borrow Request - User ID: " + userId + ", Book ID: " + bookId);

        Optional<Book> bookOptional = bookRepository.findById(bookId);
        Optional<User> userOptional = userRepository.findById(userId);

        if (bookOptional.isPresent() && userOptional.isPresent()) {
            Book bookEntity = bookOptional.get();
            User userEntity = userOptional.get();

            if (!bookEntity.isAvailable()) {
                return ResponseEntity.badRequest().body("❌ Book is already borrowed.");
            }

            bookEntity.setAvailable(false);
            bookRepository.save(bookEntity);

            // ✅ Save Transaction
            Transaction transaction = new Transaction(userEntity, bookEntity, new Date(), "borrowed");
            transactionRepository.save(transaction);

            System.out.println("✅ Book Borrowed Successfully");
            return ResponseEntity.ok("✅ Book Borrowed Successfully");
        }

        return ResponseEntity.badRequest().body("❌ Invalid User or Book ID.");
    }

    // ✅ Return Book
    @PostMapping("/return")
    public ResponseEntity<?> returnBook(@RequestParam Long transactionId) {
        Optional<Transaction> transaction = transactionRepository.findById(transactionId);

        if (transaction.isPresent() && transaction.get().getStatus().equals("borrowed")) {
            Transaction transactionEntity = transaction.get();
            transactionEntity.setStatus("returned");
            transactionEntity.setReturnDate(new Date());
            transactionRepository.save(transactionEntity);

            Book book = transactionEntity.getBook();
            book.setAvailable(true);
            bookRepository.save(book);

            return ResponseEntity.ok("✅ Book Returned Successfully");
        }
        return ResponseEntity.badRequest().body("❌ Invalid Request");
    }

    // ✅ Fetch Only Borrowed Books for a Student
    @GetMapping("/user/{userId}/borrowed")
    public ResponseEntity<List<Transaction>> getUserBorrowedBooks(@PathVariable Long userId) {
        List<Transaction> transactions = transactionRepository.findByUserIdAndStatus(userId, "borrowed");
        return ResponseEntity.ok(transactions);
    }
}
