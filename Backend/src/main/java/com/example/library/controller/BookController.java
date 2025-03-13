package com.example.library.controller;

import com.example.library.model.Book;
import com.example.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")  // ✅ Allow frontend access
@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    // Get All Active (Non-deleted) Books
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookRepository.findAllActive();
        return ResponseEntity.ok(books); // ✅ Returns 200 OK with list of books
    }

    // Get All Books (Including Deleted) - Admin Only
    @GetMapping("/all")
    public ResponseEntity<List<Book>> getAllBooksIncludingDeleted() {
        List<Book> books = bookRepository.findAllIncludingDeleted();
        return ResponseEntity.ok(books);
    }

    // Get Single Active Book by ID
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Optional<Book> book = bookRepository.findActiveById(id);
        return book.map(ResponseEntity::ok)  // ✅ Returns 200 OK if found
                   .orElseGet(() -> ResponseEntity.notFound().build()); // ❌ Returns 404 if not found
    }

    // ✅ ADD New Book
    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        if (book.getTitle() == null || book.getAuthor() == null || book.getGenre() == null) {
            return ResponseEntity.badRequest().build();  // ❌ 400 Bad Request for missing fields
        }
        book.setDeleted(false); // Ensure new books are not deleted
        Book savedBook = bookRepository.save(book);
        return ResponseEntity.status(201).body(savedBook);  // ✅ Return 201 Created with the saved book
    }

    // ✅ UPDATE Book
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book bookDetails) {
        Optional<Book> optionalBook = bookRepository.findActiveById(id);
        if (optionalBook.isEmpty()) {
            return ResponseEntity.notFound().build(); // ❌ 404 if book not found
        }
        
        Book book = optionalBook.get();
        book.setTitle(bookDetails.getTitle());
        book.setAuthor(bookDetails.getAuthor());
        book.setGenre(bookDetails.getGenre());
        book.setAvailable(bookDetails.isAvailable());

        Book updatedBook = bookRepository.save(book);
        return ResponseEntity.ok(updatedBook);
    }

    // Soft Delete Book
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        Optional<Book> optionalBook = bookRepository.findActiveById(id);
        if (optionalBook.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Book book = optionalBook.get();
        book.setDeleted(true);
        bookRepository.save(book);
        return ResponseEntity.noContent().build();
    }

    // Hard Delete Book (Admin Only)
    @DeleteMapping("/{id}/permanent")
    public ResponseEntity<Void> permanentlyDeleteBook(@PathVariable Long id) {
        if (!bookRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        bookRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
