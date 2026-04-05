package interfaces;

import core.Book;

import java.util.ArrayList;

public interface IUserActions {
    boolean borrowBook(String mail,String bookTitle);
    boolean returnBook(String mail,String bookTitle);
    ArrayList<Book> seeBooksBorrowed(String mail);
    ArrayList<Book> seeAvailableBooks();
}
