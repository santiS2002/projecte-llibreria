package persistence.dao;

import core.Book;
import core.Person;
import core.User;
import persistence.ConnectionPROJECT;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoanDAOimpl implements  LoanDAO{
    @Override
    public Loan readLoanById(int id) throws SQLException, ClassNotFoundException {

        String query = "SELECT L.*, B.TITLE, B.AUTHOR, B.GENRE, B.AVAILABLE, P.NAME, P.DNI, P.PASSWORD FROM LOANS L " +
                "JOIN BOOKS B ON L.BOOK_ID = B.ID " +
                "JOIN PERSONS P ON L.MEMBER_MAIL = P.MAIL " +
                "WHERE L.ID_LOAN = ?";

        try(
                Connection connection = ConnectionPROJECT.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ){
            Book book = null;
            Person person = null;
            Loan loan = null;

            preparedStatement.setInt(1, id); //executar l'id q volem ficar a la query

            try(ResultSet resultSet = preparedStatement.executeQuery()){

                if(resultSet.next()){
                    int idLoan = resultSet.getInt("ID_LOAN");

                    int bookId = resultSet.getInt("BOOK_ID");
                    String title = resultSet.getString("TITLE");
                    String author = resultSet.getString("AUTHOR");
                    String genre = resultSet.getString("GENRE");
                    int available = resultSet.getInt("AVAILABLE");
                    book = new Book(bookId, title, author, genre, available);

                    String mail = resultSet.getString("MEMBER_MAIL");
                    String name = resultSet.getString("NAME");
                    String dni = resultSet.getString("DNI");
                    String password = resultSet.getString("PASSWORD");
                    person = new User(mail, name, dni, password);

                    String loanDate = resultSet.getString("LOAN_DATE");
                    String dueDate = resultSet.getString("DUE_DATE");
                    String returnDate = resultSet.getString("RETURN_DATE");

                    loan = new Loan(idLoan, book, person, loanDate, dueDate, returnDate);
                    return loan;

                }


            }


        }



        return null;
    }

    @Override
    public List<Loan> readAllLoans() throws SQLException, ClassNotFoundException {

            String query = "SELECT L.*, B.TITLE, B.AUTHOR, B.GENRE, B.AVAILABLE, P.NAME, P.DNI, P.PASSWORD FROM LOANS L " +
                "JOIN BOOKS B ON L.BOOK_ID = B.ID " +
                "JOIN PERSONS P ON L.MEMBER_MAIL = P.MAIL";


            try(
                    Connection connection = ConnectionPROJECT.getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
            ){
                List<Loan> listLoans = new ArrayList<>();
                  Book book = null;
                  Person person = null;
                  Loan loan = null;

                try(ResultSet resultSet = preparedStatement.executeQuery()){

                    while(resultSet.next()){
                        int idLoan = resultSet.getInt("ID_LOAN");

                        int bookId = resultSet.getInt("BOOK_ID");
                        String title = resultSet.getString("TITLE");
                        String author = resultSet.getString("AUTHOR");
                        String genre = resultSet.getString("GENRE");
                        int available = resultSet.getInt("AVAILABLE");
                        book = new Book(bookId, title, author, genre, available);

                        String mail = resultSet.getString("MEMBER_MAIL");
                        String name = resultSet.getString("NAME");
                        String dni = resultSet.getString("DNI");
                        String password = resultSet.getString("PASSWORD");
                        person = new User(mail, name, dni, password);

                        String loanDate = resultSet.getString("LOAN_DATE");
                        String dueDate = resultSet.getString("DUE_DATE");
                        String returnDate = resultSet.getString("RETURN_DATE");

                        loan = new Loan(idLoan, book, person, loanDate, dueDate, returnDate);

                        listLoans.add(loan);
                    }
                    return listLoans;


                }



            }

    }

    @Override
    public Loan createLoan(Book book, Person person, String loanDate, String dueDate, String returnDate) throws SQLException, ClassNotFoundException {

            String query = "INSERT INTO LOANS (BOOK_ID, MEMBER_MAIL, LOAN_DATE, DUE_DATE, RETURN_DATE) VALUES (?, ?, ?, ?, ?)";
        try(
                Connection connection = ConnectionPROJECT.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ){
            Loan loan = null;

            preparedStatement.setInt(1,book.getId());
            preparedStatement.setString(2, person.getMail());
            preparedStatement.setString(3, loanDate);
            preparedStatement.setString(4, dueDate);
            preparedStatement.setString(5, returnDate);

            int rowsInserted = preparedStatement.executeUpdate();

            if(rowsInserted != 1 ){
                return null;
            }


            try(ResultSet resultSet = preparedStatement.getGeneratedKeys()){

                if(resultSet.next()){
                    int idLoanFound = resultSet.getInt(1);

                    loan = new Loan(idLoanFound, book, person, loanDate, dueDate, returnDate);
                    return loan;

                }


            }



        }

        return null;
    }

    @Override
    public boolean deleteLoanById(int id) {
        return false;
    }

    @Override
    public boolean updateLoanById(int loanId, Book book, Person person, String loanDate, String dueDate, String returnDate) {
        return false;
    }
}
