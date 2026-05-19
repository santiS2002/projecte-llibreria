package gui;

import core.Book;
import services.LibrarySystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AvailableBooks extends JFrame {
    private JTextField txtSearchTitle;
    private JButton btnSearch;
    private JTable tableBooks;
    private DefaultTableModel tableModel;
    private JButton btnBack;

    private LibrarySystem controller;
    private String email;

    public AvailableBooks(LibrarySystem controller, String email) {
        this.controller = controller;
        this.email = email;

        setTitle("Available books - User: " + email);
        setSize(750, 450);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel panelTop = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelTop.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
        panelTop.add(new JLabel("Search by title:"));
        txtSearchTitle = new JTextField(20);
        panelTop.add(txtSearchTitle);
        btnSearch = new JButton("Search on DB");
        panelTop.add(btnSearch);
        add(panelTop, BorderLayout.NORTH);

        String[] columnNames = {"ID", "Title", "Author", "Genre", "isAvailable"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableBooks = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tableBooks);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        add(scrollPane, BorderLayout.CENTER);

        JPanel panelBottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBottom.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
        btnBack = new JButton("Return to menu");
        panelBottom.add(btnBack);
        add(panelBottom, BorderLayout.SOUTH);

        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarLibrosDisponibles();
            }
        });

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AvailableBooks.this.dispose();
                new UserMenu(controller, email);
            }
        });

        cargarLibrosDisponibles();
        setVisible(true);
    }

    private void cargarLibrosDisponibles() {
        String filterTitle = txtSearchTitle.getText().trim();

        tableModel.setRowCount(0);

        try {
            List<Book> listaDisponibles = controller.searchAvailableBooksByTitle(filterTitle);

            for (Book llibre : listaDisponibles) {
                Object[] rowData = {
                        llibre.getId(),
                        llibre.getTitle(),
                        llibre.getAuthor(),
                        llibre.getGenre(),
                        (llibre.getDisponible() == 1) ? "Available" : "Borrowed"
                };
                tableModel.addRow(rowData);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Error connecting to the SQL:\n" + ex.getMessage(),
                    "Error of persistence", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
}