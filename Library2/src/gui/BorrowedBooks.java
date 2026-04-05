package gui;

import services.Manager;
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
    private Manager manager;

    public BorrowedBooks(String mail, Manager manager) {
        this.mail = mail;
        this.manager = manager;

        setContentPane(borrowedBooksList);
        DefaultListModel<String> model = new DefaultListModel<>();

        ArrayList<Book> books = manager.seeBooksBorrowed(mail);

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
                new UserMenu(manager,mail);
            }
        });
    }
}
