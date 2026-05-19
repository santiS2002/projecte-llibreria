package gui;

import services.LibrarySystem;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BorrowBook extends JFrame {
    private JTextField bookName;
    private JPanel borrowBook;
    private JButton borrow;
    private JLabel desiredBook;
    private JButton exitButton;
    private String mail;
    private LibrarySystem controller;

    public BorrowBook(LibrarySystem controller, String mail) {
        this.mail = mail;
        this.controller = controller;
        setContentPane(borrowBook);
        setTitle("User Menu");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300,200);
        setLocationRelativeTo(null);
        setVisible(true);

        borrow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = bookName.getText();

                if(controller.borrowBook(mail, name)){
                    JOptionPane.showMessageDialog(null,"Book borrowed correctly!");
                }else{
                    JOptionPane.showMessageDialog(null,"Unavailable book");
                }

                bookName.setText("");
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BorrowBook.this.dispose();
                new UserMenu(controller, mail);
            }
        });
    }
}