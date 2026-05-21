package services;

import core.Book;
import persistence.dao.BookDAO;
import persistence.dao.BookDAOimpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BookManager {
    private BookDAO bookDAO;

    public BookManager() {
        this.bookDAO = new BookDAOimpl();
    }

    public boolean addBook(Book book) {
        try {
            Book newBook = bookDAO.createBook(book.getTitle(), book.getAuthor(), book.getGenre(), true);

            if (newBook != null) {
                return true;
            }
        } catch (Exception e) {
            System.err.println("Error al insertar el libro en la BD: " + e.getMessage());
        }
        return false;
    }

    public boolean deleteBook(int id) {
        try {
            boolean deleted = bookDAO.deleteBookById(id);

            if (deleted) {
                return true;
            }
        } catch (Exception e) {
            System.err.println("Error al borrar el libro de la BD: " + e.getMessage());
        }
        return false;
    }



    public List<Book> searchAvailableBooksByTitle(String titulo) throws SQLException, ClassNotFoundException {
        return bookDAO.availableBooksByTitle(titulo);
    }


}