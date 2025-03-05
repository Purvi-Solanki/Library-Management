package ui;

import models.*;
import services.Library;
import java.util.Scanner;

public class LibraryUI {
    private Library library;
    private Scanner scanner;

    // Constructor to accept a Library instance
    public LibraryUI(Library library) {
        this.library = library;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("Welcome to the Library System!");
        System.out.println("1. Login as Member");
        System.out.println("2. Login as Librarian");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {
            memberInterface();
        } else if (choice == 2) {
            librarianInterface();
        } else {
            System.out.println("Invalid choice!");
        }
    }

    private void memberInterface() {
        System.out.println("Enter your name:");
        String name = scanner.nextLine();
        System.out.println("Enter your ID:");
        String id = scanner.nextLine();
        Member member = new Member(name, id);

        while (true) {
            System.out.println("\n1. View Books\n2. Rent Book\n3. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                library.viewBooks();
            } else if (choice == 2) {
                System.out.println("Enter book title:");
                String title = scanner.nextLine();
                for (Book book : library.getBooks()) {
                    if (book.getTitle().equalsIgnoreCase(title)) {
                        library.rentBook(member, book);
                        break;
                    }
                }
            } else {
                break;
            }
        }
    }

    private void librarianInterface() {
        System.out.println("Enter Librarian name:");
        String name = scanner.nextLine();
        System.out.println("Enter Librarian ID:");
        String id = scanner.nextLine();
        Librarian librarian = new Librarian(name, id);

        while (true) {
            System.out.println("\n1. Add Book\n2. View Rented Books\n3. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.println("Enter book title:");
                String title = scanner.nextLine();
                System.out.println("Enter author:");
                String author = scanner.nextLine();
                System.out.println("Enter ISBN:");
                String isbn = scanner.nextLine();
                librarian.addBook(library, new Book(title, author, isbn));
            } else if (choice == 2) {
                librarian.viewRentedBooks(library);
            } else {
                break;
            }
        }
    }
}
