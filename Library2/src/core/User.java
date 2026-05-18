package core;

import persistence.dao.Loan;

import java.util.List;

public class User extends Person {
    public User(String mail, String name, String dni, String password) {
        super(mail, name, dni, password);
    }
}
