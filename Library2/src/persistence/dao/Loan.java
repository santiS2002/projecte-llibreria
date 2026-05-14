package persistence.dao;

import core.Book;
import core.Person;

import java.sql.Date;

public class Loan {
     private int loanId;
    private Book book;
    private Person person;
    private Date loanDate;
    private Date dueDate;
    private Date returnDate;

    public Loan(int loanId, Book book, Person person, Date loanDate, Date dueDate, Date returnDate) {
        this.loanId = loanId;
        this.book = book;
        this.person = person;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
    }

    public Loan() {
    }

    public int getLoanId() {
        return loanId;
    }

    public void setLoanId(int loanId) {
        this.loanId = loanId;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return "Loans{" +
                "loanId=" + loanId +
                ", book=" + book +
                ", person=" + person +
                ", loanDate=" + loanDate +
                ", dueDate=" + dueDate +
                ", returnDate=" + returnDate +
                '}';
    }
}
