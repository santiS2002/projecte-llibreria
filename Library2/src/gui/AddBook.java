package gui;

import core.Book;
import services.LibrarySystem;
import javax.swing.*;
import java.awt.*;

public class AddBook extends JFrame {
    private JTextField txtTitle;
    private JTextField txtAuthor;
    private JTextField txtGenre;
    private JButton btnAdd;
    private JButton btnBack;
    private LibrarySystem controller;
    private String email;

    public AddBook(LibrarySystem controller, String email) {
        this.controller = controller;
        this.email = email;

        setTitle("Add new book");
        setSize(350, 250);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2, 10, 10));

        add(new JLabel(" Title:"));
        txtTitle = new JTextField();
        add(txtTitle);

        add(new JLabel(" Author:"));
        txtAuthor = new JTextField();
        add(txtAuthor);

        add(new JLabel(" Genre:"));
        txtGenre = new JTextField();
        add(txtGenre);

        btnBack = new JButton("Return");
        add(btnBack);

        btnAdd = new JButton("Save");
        add(btnAdd);

        btnAdd.addActionListener(e -> {
            String title = txtTitle.getText().trim();
            String author = txtAuthor.getText().trim();
            String genre = txtGenre.getText().trim();

            if (title.isEmpty() || author.isEmpty() || genre.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All the fields are necessary");
                return;
            }

            Book book = new Book(0, title, author, genre, 1);
            if (controller.addBook(book)) {
                JOptionPane.showMessageDialog(this, "Book added correctly");
                txtTitle.setText("");
                txtAuthor.setText("");
                txtGenre.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Error adding the book");
            }
        });

        btnBack.addActionListener(e -> {
            dispose();
            new UserMenu(controller, email);
        });

        setVisible(true);
    }
}