package persistence.dao;

import core.Book;
import core.Person;

import java.sql.SQLException;
import java.util.List;

public interface BookDAO {
    Book readBookById(int id) throws SQLException, ClassNotFoundException;
    List<Book> readAllBooks() throws SQLException, ClassNotFoundException;
    List<Book> availableBooksByTitle(String Title) throws SQLException, ClassNotFoundException;
    Book createBook(String title, String author, String genre, boolean disponible) throws SQLException, ClassNotFoundException;
    boolean deleteBookById(int id) throws SQLException, ClassNotFoundException;
    boolean updateBookById(int id, String title, String author, String genre, boolean disponible) throws SQLException, ClassNotFoundException;
}
