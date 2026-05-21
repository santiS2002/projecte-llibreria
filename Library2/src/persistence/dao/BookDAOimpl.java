package persistence.dao;

import core.Book;
import persistence.ConnectionPROJECT;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAOimpl implements BookDAO {

    @Override
    public Book readBookById(int id) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM BOOKS WHERE ID = ?";

        try (
                Connection connection = ConnectionPROJECT.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new Book(
                            resultSet.getInt("ID"),
                            resultSet.getString("TITLE"),
                            resultSet.getString("AUTHOR"),
                            resultSet.getString("GENRE"),
                            resultSet.getInt("AVAILABLE")
                    );
                }
            }
        }
        return null;
    }

    @Override
    public List<Book> readAllBooks() throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM BOOKS";
        List<Book> listBooks = new ArrayList<>();

        try (
                Connection connection = ConnectionPROJECT.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery()
        ) {
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
        return listBooks;
    }

    @Override
    public List<Book> availableBooksByTitle(String title) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM BOOKS WHERE TITLE LIKE ? AND AVAILABLE = 1";
        List<Book> listBooks = new ArrayList<>();

        try (
                Connection connection = ConnectionPROJECT.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, "%" + title + "%");

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
    public Book createBook(String title, String author, String genre, boolean disponible) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO BOOKS (TITLE, AUTHOR, GENRE, AVAILABLE) VALUES (?, ?, ?, ?)";

        try (
                Connection connection = ConnectionPROJECT.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)
        ) {
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, author);
            preparedStatement.setString(3, genre);
            preparedStatement.setInt(4, disponible ? 1 : 0);

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted != 1) {
                return null;
            }

            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    int generatedId = resultSet.getInt(1);
                    return new Book(generatedId, title, author, genre, disponible ? 1 : 0);
                }
            }
        }
        return null;
    }

    @Override
    public boolean deleteBookById(int id) throws SQLException, ClassNotFoundException {
        String query = "DELETE FROM BOOKS WHERE ID = ?";

        try (
                Connection connection = ConnectionPROJECT.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setInt(1, id);
            int rowsDeleted = preparedStatement.executeUpdate();
            return rowsDeleted > 0;
        }
    }

    @Override
    public boolean updateBookById(int id, String title, String author, String genre, boolean disponible) throws SQLException, ClassNotFoundException {
        String query = "UPDATE BOOKS SET TITLE = ?, AUTHOR = ?, GENRE = ?, AVAILABLE = ? WHERE ID = ?";

        try (
                Connection connection = ConnectionPROJECT.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, author);
            preparedStatement.setString(3, genre);
            preparedStatement.setInt(4, disponible ? 1 : 0);
            preparedStatement.setInt(5, id);

            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;
        }
    }
}