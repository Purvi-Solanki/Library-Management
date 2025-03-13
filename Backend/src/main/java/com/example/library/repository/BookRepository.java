package com.example.library.repository;

import com.example.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    // Find all non-deleted books
    @Query("SELECT b FROM Book b WHERE b.isDeleted = false")
    List<Book> findAllActive();

    // Find non-deleted book by id
    @Query("SELECT b FROM Book b WHERE b.id = ?1 AND b.isDeleted = false")
    Optional<Book> findActiveById(Long id);

    // Find all books (including deleted) for admin purposes
    @Query("SELECT b FROM Book b")
    List<Book> findAllIncludingDeleted();

    // Custom query methods (if needed)
}
