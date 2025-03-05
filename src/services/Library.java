package services;

import models.Book;
import models.Member;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class Library {
    private List<Book> books;
    private HashMap<Member, List<Book>> rentedBooks;

    public Library() {
        this.books = new ArrayList<>();
        this.rentedBooks = new HashMap<>();
    }

    // ✅ New getter method to access books list
    public List<Book> getBooks() {
        return books;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void viewBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available in the library.");
            return;
        }
        for (Book book : books) {
            book.displayBook();
        }
    }

    public void rentBook(Member member, Book book) {
        if (!books.contains(book)) {
            System.out.println("Error: Book is not available in the library.");
            return;
        }

        books.remove(book);

        if (!rentedBooks.containsKey(member)) {
            rentedBooks.put(member, new ArrayList<>());
        }
        rentedBooks.get(member).add(book);

        System.out.println(member.getName() + " rented the book: " + book.getTitle());
    }

    public void viewRentedBooks() {
        if (rentedBooks.isEmpty()) {
            System.out.println("No books have been rented yet.");
            return;
        }

        for (Member member : rentedBooks.keySet()) {
            System.out.println(member.getName() + " has rented: ");
            for (Book book : rentedBooks.get(member)) {
                System.out.println(" - " + book.getTitle());
            }
        }
    }

    // ✅ New method to return a rented book
    public void returnBook(Member member, Book book) {
        if (!rentedBooks.containsKey(member) || !rentedBooks.get(member).contains(book)) {
            System.out.println("Error: This book was not rented by " + member.getName());
            return;
        }

        rentedBooks.get(member).remove(book);
        books.add(book);
        System.out.println(member.getName() + " returned the book: " + book.getTitle());

        if (rentedBooks.get(member).isEmpty()) {
            rentedBooks.remove(member);
        }
    }
}
