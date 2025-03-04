package src.models;

import java.util.ArrayList;
import java.util.List;

public class Member extends Person {
    private String memberID;
    private List<Book> borrowedBooks;

    public Member(String name, String contactInfo, String memberID) {
        super(name, contactInfo);
        this.memberID = memberID;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getMemberID() {
        return memberID;
    }

    public void borrowBook(Book book) {
        if (book.isAvailable()) {
            borrowedBooks.add(book);
            book.borrowBook();
            System.out.println(name + " borrowed " + book.getTitle());
        } else {
            System.out.println("Book is not available.");
        }
    }

    public void returnBook(Book book) {
        if (borrowedBooks.remove(book)) {
            book.returnBook();
            System.out.println(name + " returned " + book.getTitle());
        }
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Member ID: " + memberID);
    }
}
