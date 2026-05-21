package services;

import core.Book;
import core.Person;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LibrarySystem {
    private BookManager bookManager;
    private UserManager userManager;
    private LoanManager loanManager;

    public LibrarySystem() {
        this.bookManager = new BookManager();
        this.userManager = new UserManager();
        this.loanManager = new LoanManager();
    }

    public boolean addBook(Book book) {
        return bookManager.addBook(book);
    }

    public boolean borrowBook(String mail, String bookTitle) {
        return loanManager.borrowBook(mail, bookTitle);
    }

    public boolean returnBook(String mail, String bookTitle) {
        return loanManager.returnBook(mail, bookTitle);
    }

    public ArrayList<Book> seeBooksBorrowed(String mail) {
        return loanManager.seeBooksBorrowed(mail);
    }

    public boolean register(String mail, String name, String dni, String password) {
        return userManager.register(mail, name, dni, password);
    }

    public Person login(String mail, String password) {
        return userManager.login(mail, password);
    }

    public void sendSecurityAlert(String mail) {
        userManager.sendSecurityAlert(mail);
    }

    public boolean isAdmin(String mail) {

        return userManager.isAdmin(mail);
    }

    public List<Book> searchAvailableBooksByTitle(String titulo) throws SQLException, ClassNotFoundException {
        return bookManager.searchAvailableBooksByTitle(titulo);
    }
}