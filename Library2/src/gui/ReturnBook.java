package gui;

import services.LibrarySystem;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReturnBook extends JFrame{
    private JTextField bookToReturnField;
    private JPanel returnBookPanel;
    private JButton returnBook;
    private JLabel bookNameLabel;
    private JButton exitButton;
    private String mail;
    private LibrarySystem controller;

    public ReturnBook(LibrarySystem controller, String mail) {
        this.controller = controller;
        this.mail = mail;

        setContentPane(returnBookPanel);
        setTitle("Return Book");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300,200);
        setLocationRelativeTo(null);
        setVisible(true);

        returnBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = bookToReturnField.getText();

                if (controller.returnBook(mail, name)) {
                    JOptionPane.showMessageDialog(null, name + " returned successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "You do not have this book on loan.");
                }

                bookToReturnField.setText("");
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ReturnBook.this.dispose();
                new UserMenu(controller, mail);
            }
        });
    }
}