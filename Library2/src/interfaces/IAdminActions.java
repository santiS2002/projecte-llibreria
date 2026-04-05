package interfaces;

import core.Book;

public interface IAdminActions {
    boolean addBook(Book book);
    boolean deleteBook(int id);
}
