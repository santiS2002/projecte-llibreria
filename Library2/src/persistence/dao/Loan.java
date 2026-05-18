package persistence.dao;

import core.Book;
import core.Person;

import java.sql.Date;

public class Loan {
     private int loanId;
    private Book book;
    private Person person;
    private String loanDate;
    private String dueDate;
    private String returnDate;

    public Loan(int loanId, Book book, Person person, String loanDate, String dueDate, String returnDate) {
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

    public String getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(String loanDate) {
        this.loanDate = loanDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return "Loans{" +
                "loanId=" + loanId +
                ", book=" + (book != null ? "id book: " + book.getId() + " book title: " + book.getTitle() + "book author: " + book.getAuthor() + "book genre: " + book.getGenre() + "is available? " + book.getDisponible() : " fk is null ") +
                ", person=" + (person != null ? "id person: " + person.getMail() + "person name: " + person.getName() + "person dni: " + person.getDni() + " password: " + person.getPassword() : " fk is null ") +
                ", loanDate=" + loanDate +
                ", dueDate=" + dueDate +
                ", returnDate=" + returnDate +
                '}';
    }
}
