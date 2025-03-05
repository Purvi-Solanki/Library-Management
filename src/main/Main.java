package main;

import models.Book;
import models.Member;
import models.Librarian;
import services.Library;
import ui.LibraryUI;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();
        LibraryUI libraryUI = new LibraryUI(library); // Ensure LibraryUI uses the same instance

        System.out.println("Welcome to the Library Management System!");

        while (true) {
            System.out.println("\nSelect Role:");
            System.out.println("1. User");
            System.out.println("2. Librarian");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            int roleChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (roleChoice == 3) {
                System.out.println("Exiting system. Goodbye!");
                break;
            }

            switch (roleChoice) {
                case 1: // User (Member)
                    System.out.print("Enter your name: ");
                    String userName = scanner.nextLine();
                    System.out.print("Enter your ID: ");
                    String userId = scanner.nextLine();
                    Member member = new Member(userName, userId);

                    while (true) {
                        System.out.println("\nUser Menu:");
                        System.out.println("1. View Books");
                        System.out.println("2. Rent a Book");
                        System.out.println("3. Go Back");
                        System.out.print("Enter choice: ");
                        int userChoice = scanner.nextInt();
                        scanner.nextLine(); // Consume newline

                        if (userChoice == 3)
                            break;

                        switch (userChoice) {
                            case 1:
                                library.viewBooks();
                                break;
                            case 2:
                                System.out.print("Enter book title to rent: ");
                                String bookTitle = scanner.nextLine();
                                Book bookToRent = null;

                                for (Book b : library.getBooks()) {
                                    if (b.getTitle().equalsIgnoreCase(bookTitle)) {
                                        bookToRent = b;
                                        break;
                                    }
                                }

                                if (bookToRent != null) {
                                    library.rentBook(member, bookToRent);
                                } else {
                                    System.out.println("Book not found.");
                                }
                                break;
                            default:
                                System.out.println("Invalid choice. Try again.");
                        }
                    }
                    break;

                case 2: // Librarian
                    System.out.print("Enter Librarian name: ");
                    String librarianName = scanner.nextLine();
                    System.out.print("Enter Librarian ID: ");
                    String librarianId = scanner.nextLine();
                    Librarian librarian = new Librarian(librarianName, librarianId);

                    while (true) {
                        System.out.println("\nLibrarian Menu:");
                        System.out.println("1. Add Book");
                        System.out.println("2. View Rented Books");
                        System.out.println("3. View All Books");
                        System.out.println("4. Go Back");
                        System.out.print("Enter choice: ");
                        int librarianChoice = scanner.nextInt();
                        scanner.nextLine(); // Consume newline

                        if (librarianChoice == 4)
                            break;

                        switch (librarianChoice) {
                            case 1:
                                System.out.print("Enter book title: ");
                                String title = scanner.nextLine();
                                System.out.print("Enter author: ");
                                String author = scanner.nextLine();
                                System.out.print("Enter ISBN: ");
                                String isbn = scanner.nextLine();

                                Book newBook = new Book(title, author, isbn);
                                librarian.addBook(library, newBook); // ✅ Fix: Ensure book is added to library
                                System.out.println("Book added successfully!");
                                break;
                            case 2:
                                library.viewRentedBooks();
                                break;
                            case 3:
                                library.viewBooks();
                                break;
                            default:
                                System.out.println("Invalid choice. Try again.");
                        }
                    }
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
        scanner.close();
    }
}
