package persistence.dao;

import core.Book;
import core.Loan;
import core.Person;

import java.sql.SQLException;
import java.util.List;

public interface LoanDAO {

    Loan readLoanById(int id) throws SQLException, ClassNotFoundException;
    List<Loan> readAllLoans() throws SQLException, ClassNotFoundException;
    Loan createLoan(Book book, Person person, String loanDate, String dueDate, String returnDate) throws SQLException, ClassNotFoundException;
    boolean deleteLoanById(int id) throws SQLException, ClassNotFoundException;
    boolean updateLoanById(int loanId, Book book, Person person, String loanDate, String dueDate, String returnDate);
    boolean returnLoan(String mail, int bookId) throws SQLException, ClassNotFoundException ;
    boolean createLoan(String mail, int bookId)  throws SQLException, ClassNotFoundException;;

}
