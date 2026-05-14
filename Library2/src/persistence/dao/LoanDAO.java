package persistence.dao;

import core.Book;
import core.Person;

import java.sql.Date;
import java.util.List;

public interface LoanDAO {

    Loan readLoanById(int id);
    List<Loan> readAllLoans();
    Loan createLoan(Book book, Person person, Date loanDate, Date dueDate, Date returnDate);
    boolean deleteLoanById(int id);
    boolean updateLoanById(int loanId, Book book, Person person, Date loanDate, Date dueDate, Date returnDate);

}
