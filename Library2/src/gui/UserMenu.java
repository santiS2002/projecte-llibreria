package gui;

import services.LibrarySystem;
import javax.swing.*;

public class UserMenu extends JFrame {
    private JPanel userMenuPanel;
    private JLabel userTitleLabel;
    private JButton availableBooksButton;
    private JButton borrowedBooksButton;
    private JButton returnBookButton;
    private JButton borrowBookButton;
    private JButton addBookButton;
    private JButton exitButton;
    private String email;
    private LibrarySystem controller;

    public UserMenu(LibrarySystem controller, String email) {
        this.controller = controller;
        this.email = email;

        setContentPane(userMenuPanel);
        setTitle("User Menu - " + email);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 450);
        setLocationRelativeTo(null);
        setVisible(true);

        addBookButton.setVisible(controller.isAdmin(email));
        userMenuPanel.revalidate();
        userMenuPanel.repaint();

        borrowBookButton.addActionListener(e -> {
            dispose();
            new BorrowBook(controller, email);
        });

        returnBookButton.addActionListener(e -> {
            dispose();
            new ReturnBook(controller, email);
        });

        borrowedBooksButton.addActionListener(e -> {
            dispose();
            new BorrowedBooks(email, controller);
        });

        availableBooksButton.addActionListener(e -> {
            dispose();
            new AvailableBooks(controller, email);
        });

        addBookButton.addActionListener(e -> {
            dispose();
            new AddBook(controller, email);
        });

        exitButton.addActionListener(e -> {
            dispose();
            new MainPage(controller);
        });
    }
}