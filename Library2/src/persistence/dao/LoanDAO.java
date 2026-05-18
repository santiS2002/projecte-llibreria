package persistence.dao;

import core.Book;
import core.Person;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface LoanDAO {

    Loan readLoanById(int id) throws SQLException, ClassNotFoundException;
    List<Loan> readAllLoans() throws SQLException, ClassNotFoundException;
    Loan createLoan(Book book, Person person, String loanDate, String dueDate, String returnDate) throws SQLException, ClassNotFoundException;
    boolean deleteLoanById(int id);
    boolean updateLoanById(int loanId, Book book, Person person, String loanDate, String dueDate, String returnDate);

}
