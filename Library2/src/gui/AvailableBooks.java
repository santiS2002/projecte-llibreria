package gui;
import services.Manager;
import core.Book;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AvailableBooks extends JFrame {
    private JList<String> list;
    private JPanel availableBooksList;
    private JButton exitButton;
    private String mail;
    private Manager manager;

    public AvailableBooks(Manager manager, String mail) {
        this.manager = manager;
        this.mail = mail;

        setContentPane(availableBooksList);

        DefaultListModel<String> model = new DefaultListModel<>();

        ArrayList<Book> availables = manager.seeAvailableBooks();

        if(availables != null){
            for(Book book: availables){
                model.addElement(book.toString());
            }
        }

        list.setModel(model);
        setTitle("Available books");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300,200);
        setLocationRelativeTo(null);
        setVisible(true);

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            AvailableBooks.this.dispose();
            new UserMenu(manager,mail);
            }
        });
    }
}
