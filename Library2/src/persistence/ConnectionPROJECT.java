package persistence;

import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionPROJECT {

    //    private static final String DB_URL = "jdbc:sqlite:data/chinook.sqlite";
    private static final String DB_URL =
            "jdbc:sqlite:" + Path.of("identifier.sqlite").toAbsolutePath();
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");

        return DriverManager.getConnection(DB_URL);
    }

    public static void main(String[] args) {
        try (Connection conn = ConnectionPROJECT.getConnection()) {
            System.out.println("Connected to SQLite database!");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
    }



}
