package gui;

import services.LibrarySystem;
import core.Book;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BorrowedBooks extends JFrame {
    private JPanel borrowedBooksList;
    private JList<String> list;
    private JButton exit;
    private String mail;
    private LibrarySystem controller;

    public BorrowedBooks(String mail, LibrarySystem controller) {
        this.mail = mail;
        this.controller = controller;

        setContentPane(borrowedBooksList);
        DefaultListModel<String> model = new DefaultListModel<>();

        ArrayList<Book> books = controller.seeBooksBorrowed(mail);

        if(books != null){
            for(Book book: books){
                model.addElement(book.toString());
            }
        }

        list.setModel(model);
        setTitle("Books Borrowed");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300,200);
        setLocationRelativeTo(null);
        setVisible(true);

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BorrowedBooks.this.dispose();
                new UserMenu(controller, mail);
            }
        });
    }
}