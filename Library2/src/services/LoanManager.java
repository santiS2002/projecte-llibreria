package services;

import core.Book;
import persistence.dao.BookDAO;
import persistence.dao.BookDAOimpl;
import persistence.dao.PersonDAO;
import persistence.dao.PersonDAOimpl;
import persistence.dao.LoanDAO;
import persistence.dao.LoanDAOimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LoanManager {
    private BookDAO bookDAO;
    private PersonDAO personDAO;
    private LoanDAO loanDAO;
    private HashMap<String, ArrayList<Book>> borrowedBooksSession;

    public LoanManager() {
        this.bookDAO = new BookDAOimpl();
        this.personDAO = new PersonDAOimpl();
        this.loanDAO = new LoanDAOimpl();
        this.borrowedBooksSession = new HashMap<>();
    }

    private void asegurarSesionUsuario(String mail) {
        if (!borrowedBooksSession.containsKey(mail)) {
            borrowedBooksSession.put(mail, new ArrayList<>());

            try {
                List<Book> dbBooks = personDAO.readPersonBooksByAvailable(mail);
                if (dbBooks != null) {
                    borrowedBooksSession.get(mail).addAll(dbBooks);
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public boolean borrowBook(String mail, String bookTitle) {
        asegurarSesionUsuario(mail);
        try {
            List<Book> books = bookDAO.availableBooksByTitle(bookTitle);

            if (!books.isEmpty()) {
                Book book = books.get(0);

                if (book.getDisponible() == 1) {
                    boolean updated = bookDAO.updateBookById(book.getId(), book.getTitle(), book.getAuthor(), book.getGenre(), false);

                    if (updated) {
                        loanDAO.createLoan(mail, book.getId());
                        book.setDisponible(0);
                        borrowedBooksSession.get(mail).add(book);
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    public boolean returnBook(String mail, String bookTitle) {
        asegurarSesionUsuario(mail);
        ArrayList<Book> userBooks = borrowedBooksSession.get(mail);

        for (int i = 0; i < userBooks.size(); i++) {
            Book book = userBooks.get(i);

            if (book.getTitle().equalsIgnoreCase(bookTitle)) {
                try {
                    boolean updated = bookDAO.updateBookById(book.getId(), book.getTitle(), book.getAuthor(), book.getGenre(), true);

                    if (updated) {
                        loanDAO.returnLoan(mail, book.getId());
                        book.setDisponible(1);
                        userBooks.remove(i);
                        return true;
                    }
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }
        return false;
    }

    public ArrayList<Book> seeBooksBorrowed(String mail) {
        asegurarSesionUsuario(mail);
        return borrowedBooksSession.get(mail);
    }
}