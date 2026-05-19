package persistence.dao;

import core.Person;
import core.Book;

import java.sql.SQLException;
import java.util.List;

public interface PersonDAO {

    Person readPersonByEmail(String email) throws SQLException, ClassNotFoundException;
    List<Person> readAllPersons() throws SQLException, ClassNotFoundException;
    List<Book> readPersonBooksByAvailable(String email) throws SQLException, ClassNotFoundException; //email es la pk del person
    Person createPerson(String mail, String name, String dni, String password) throws SQLException, ClassNotFoundException;
    boolean deletePersonByEmail(String email) throws SQLException, ClassNotFoundException;
    boolean updatePersonByEmail(String mail, String name, String dni, String password) throws SQLException, ClassNotFoundException;

}
