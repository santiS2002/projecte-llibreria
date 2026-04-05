package gui;

import services.Manager;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPage extends JFrame {
    private JButton loginButton;
    private JPanel mainPanel;
    private JButton registerButton;
    private JLabel labelMenu;
    private Manager manager;

    public MainPage(Manager manager) {
        this.manager = manager;

        setContentPane(mainPanel);
        setTitle("Library Management System");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300,200);
        setLocationRelativeTo(null);
        setVisible(true);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainPage.this.dispose();
                new Login(manager);

            }
        });
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainPage.this.dispose();
                new Register(manager);
            }
        });
    }
}
