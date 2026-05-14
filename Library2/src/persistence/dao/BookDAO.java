package persistence.dao;

import core.Book;
import core.Person;

import java.util.List;

public interface BookDAO {
    Book readBookById(int id);
    List<Book> readAllBooks();
    List<Book> availableBooksByTitle(String Title);
    Book createBook(String title, String author, String genre, boolean disponible);
    boolean deleteBookById(int id);
    boolean updateBookById(int id, String title, String author, String genre, boolean disponible);
}
