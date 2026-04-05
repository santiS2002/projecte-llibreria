package gui;

import services.Manager;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserMenu extends JFrame {
    private JPanel userMenuPanel;
    private JLabel userTitleLabel;
    private JButton availableBooksButton;
    private JButton borrowedBooksButton;
    private JButton returnBookButton;
    private JButton borrowBookButton;
    private JButton exitButton;

    private String email;
    private Manager manager;

    public UserMenu(Manager manager, String email) {
        this.manager = manager;
        this.email = email;

        setContentPane(userMenuPanel);
        setTitle("User Menu - " + email);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setVisible(true);

        borrowBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserMenu.this.dispose();
                new BorrowBook(manager, email);
            }
        });

        returnBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserMenu.this.dispose();
                new ReturnBook(manager, email);
            }
        });

        borrowedBooksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserMenu.this.dispose();
                new BorrowedBooks(email,manager);
            }
        });

        availableBooksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserMenu.this.dispose();
                new AvailableBooks(manager, email);
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserMenu.this.dispose();
                new MainPage(manager);
            }
        });
    }
}