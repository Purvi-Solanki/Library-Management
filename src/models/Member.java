package models;

import java.util.ArrayList;
import java.util.List;

public class Member extends Person {
    private List<Book> rentedBooks;

    public Member(String name, String id) {
        super(name, id);
        this.rentedBooks = new ArrayList<>();
    }

    public void rentBook(Book book) {
        rentedBooks.add(book);
        System.out.println(name + " rented " + book.getTitle());
    }

    public List<Book> getRentedBooks() {
        return rentedBooks;
    }

    @Override
    public void displayDetails() {
        System.out.println("Member ID: " + id + ", Name: " + name);
    }
}
