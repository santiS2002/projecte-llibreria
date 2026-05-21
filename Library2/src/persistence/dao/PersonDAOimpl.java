package persistence.dao;

import core.Book;
import core.Person;
import core.User;
import persistence.ConnectionPROJECT;

import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDAOimpl implements PersonDAO {

    @Override
    public Person readPersonByEmail(String email) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM PERSONS WHERE MAIL = ?";
        try (
                Connection connection = ConnectionPROJECT.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, email);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String rol = resultSet.getString("ROL");

                    if ("ADMIN".equalsIgnoreCase(rol)) {
                        return new core.Admin(
                                resultSet.getString("MAIL"),
                                resultSet.getString("NAME"),
                                resultSet.getString("DNI"),
                                resultSet.getString("PASSWORD")
                        );
                    } else {
                        return new core.User(
                                resultSet.getString("MAIL"),
                                resultSet.getString("NAME"),
                                resultSet.getString("DNI"),
                                resultSet.getString("PASSWORD")
                        );
                    }
                }
            }
        }
        return null;
    }

    @Override
    public List<Person> readAllPersons() throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM PERSONS";
        List<Person> listPersons = new ArrayList<>();

        try (
                Connection connection = ConnectionPROJECT.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            while (resultSet.next()) {
                String rol = resultSet.getString("ROL");
                Person person;

                if ("ADMIN".equalsIgnoreCase(rol)) {
                    person = new core.Admin(
                            resultSet.getString("MAIL"),
                            resultSet.getString("NAME"),
                            resultSet.getString("DNI"),
                            resultSet.getString("PASSWORD")
                    );
                } else {
                    person = new core.User(
                            resultSet.getString("MAIL"),
                            resultSet.getString("NAME"),
                            resultSet.getString("DNI"),
                            resultSet.getString("PASSWORD")
                    );
                }
                listPersons.add(person);
            }
        }
        return listPersons;
    }

    @Override
    public List<Book> readPersonBooksByAvailable(String email) throws SQLException, ClassNotFoundException {
        // Selecciona els llibres assignats a aquesta persona on el préstec encara no s'ha retornat (RETURN_DATE IS NULL)
        String query = "SELECT B.* FROM BOOKS B " +
                       "JOIN LOANS L ON B.ID = L.BOOK_ID " +
                       "WHERE L.MEMBER_MAIL = ? AND L.RETURN_DATE IS NULL";

        List<Book> listBooks = new ArrayList<>();

        try (
                Connection connection = ConnectionPROJECT.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, email);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Book book = new Book(
                            resultSet.getInt("ID"),
                            resultSet.getString("TITLE"),
                            resultSet.getString("AUTHOR"),
                            resultSet.getString("GENRE"),
                            resultSet.getInt("AVAILABLE")
                    );
                    listBooks.add(book);
                }
            }
        }
        return listBooks;
    }

    @Override
    public Person createPerson(String mail, String name, String dni, String password) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO PERSONS (MAIL, NAME, DNI, PASSWORD, ROL) VALUES (?, ?, ?, ?, 'USER')";

        try (
                Connection connection = ConnectionPROJECT.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, mail);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, dni);
            preparedStatement.setString(4, password);

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted == 1) {
                return new core.User(mail, name, dni, password);
            }
        }
        return null;
    }

    @Override
    public boolean deletePersonByEmail(String email) throws SQLException, ClassNotFoundException {
        String query = "DELETE FROM PERSONS WHERE MAIL = ?";

        try (
                Connection connection = ConnectionPROJECT.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, email);
            int rowsDeleted = preparedStatement.executeUpdate();
            return rowsDeleted > 0;
        }
    }

    @Override
    public boolean updatePersonByEmail(String mail, String name, String dni, String password) throws SQLException, ClassNotFoundException {
        String query = "UPDATE PERSONS SET NAME = ?, DNI = ?, PASSWORD = ? WHERE MAIL = ?";

        try (
                Connection connection = ConnectionPROJECT.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, dni);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, mail);

            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;
        }
    }
}