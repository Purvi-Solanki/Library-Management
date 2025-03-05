package models;

import services.Library; // ✅ Add this line to import Library.java

public class Librarian extends Person {
    public Librarian(String name, String id) {
        super(name, id);
    }

    public void addBook(Library library, Book book) { // ✅ Now Library is recognized
        library.addBook(book);
        System.out.println("Librarian " + name + " added: " + book.getTitle());
    }

    public void viewRentedBooks(Library library) { // ✅ Library is now resolved
        library.viewRentedBooks();
    }

    @Override
    public void displayDetails() {
        System.out.println("Librarian ID: " + id + ", Name: " + name);
    }
}
