package persistence.dao;

import core.Person;
import core.Book;
import java.util.List;

public interface PersonDAO {

    Person readPersonByEmail(String email);
    List<Person> readAllPersons();
    List<Book> readPersonBooksByAvailable(String email); //email es la pk del person
    Person createPerson(String mail, String name, String dni, String password);
    boolean deletePersonByEmail(String email);
    boolean updatePersonByEmail(String mail, String name, String dni, String password);

}
