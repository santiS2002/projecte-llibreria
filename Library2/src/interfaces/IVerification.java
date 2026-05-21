package interfaces;

import core.Person;

public interface IVerification {
    boolean register(String mail, String name, String dni, String password);
    Person login(String mail,String password);

}
