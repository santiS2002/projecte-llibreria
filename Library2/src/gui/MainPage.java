package gui;

import services.LibrarySystem;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPage extends JFrame {
    private JButton loginButton;
    private JPanel mainPanel;
    private JButton registerButton;
    private JLabel labelMenu;
    private JButton addBookButton;
    private LibrarySystem controller;

    public MainPage(LibrarySystem controller) {
        this.controller = controller;

        setContentPane(mainPanel);
        setTitle("Library Management System");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 250);
        setLocationRelativeTo(null);
        setVisible(true);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainPage.this.dispose();
                new Login(controller);
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainPage.this.dispose();
                new Register(controller);
            }
        });


    }
}