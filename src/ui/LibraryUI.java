package ui;

import services.Library;

import javax.swing.*;

import models.Book;
import models.Member;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LibraryUI {
    private Library library;

    public LibraryUI() {
        library = new Library();
        initializeUI();
    }

    private void initializeUI() {
        JFrame frame = new JFrame("Library Management System");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        JButton addBookBtn = new JButton("Add Book");
        JButton listBooksBtn = new JButton("List Books");

        addBookBtn.addActionListener(e -> {
            String title = JOptionPane.showInputDialog("Enter Book Title:");
            String author = JOptionPane.showInputDialog("Enter Author Name:");
            Book book = new Book(String.valueOf(System.currentTimeMillis()), title, author);
            library.addBook(book);
            JOptionPane.showMessageDialog(null, "Book Added Successfully!");
        });

        listBooksBtn.addActionListener(e -> {
            StringBuilder booksList = new StringBuilder("Books in Library:\n");
            for (Book book : library.getBooks()) { // ✅ Use getter method
                booksList.append(book.getTitle()).append(" by ").append(book.getAuthor()).append("\n");
            }
            JOptionPane.showMessageDialog(null, booksList.toString());
        });

        frame.add(addBookBtn);
        frame.add(listBooksBtn);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new LibraryUI();
    }
}
