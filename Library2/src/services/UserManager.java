package services;

import core.Admin;
import core.Person;
import persistence.dao.PersonDAO;
import persistence.dao.PersonDAOimpl;
import services.emails.EnviarEmails;

import java.nio.file.Paths;

public class UserManager {
    private PersonDAO personDAO;

    public UserManager() {
        this.personDAO = new PersonDAOimpl();
    }

    public boolean register(String mail, String name, String dni, String password) {
        if (!calculDni(dni)) {
            return false;
        }

        if (!validacioPass(password)) {
            return false;
        }
        try {
            Person existingPerson = personDAO.readPersonByEmail(mail);
            if (existingPerson != null) {
                return false;
            }

            Person newUser = personDAO.createPerson(mail, name, dni, password);
            if (newUser != null) {
                final String finalName = name;
                final String finalMail = mail;

                new Thread(() -> {
                    try {
                        EnviarEmails welcomeEmail = new EnviarEmails(finalMail, "Welcome", "Registration", finalName);
                        welcomeEmail.enviarEmailBenvinguda();
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                }).start();
                return true;
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    public Person login(String mail, String password) {
        try {
            Person person = personDAO.readPersonByEmail(mail);
            if (person != null && person.getPassword().equals(password)) {
                return person;
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public void sendSecurityAlert(String mail) {
        new Thread(() -> {
            try {
                String name = "User";
                Person p = personDAO.readPersonByEmail(mail);
                if (p != null) {
                    name = p.getName();
                }

                EnviarEmails emailService = new EnviarEmails(mail, "Security Alert", "Too many attempts", name);
                emailService.enviarCorreuAlerta();
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }).start();
    }

    public boolean isAdmin(String mail) {
        try {
            Person p = personDAO.readPersonByEmail(mail);
            return p instanceof Admin;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean calculDni(String numDni) {
        try {
            if (numDni == null || numDni.length() != 9) return false;

            String[] letrasDni = {"T","R","W","A","G","M","Y","F","P",
                    "D","X","B","N","J","Z","S","Q","V",
                    "H","L","C","K","E"};

            String substringDni = numDni.substring(0, 8);
            String substringLetra = numDni.substring(8, 9);
            int dniEnter = Integer.parseInt(substringDni);

            for (int i = 0; i < letrasDni.length; i++) {
                if (dniEnter % 23 == i) {
                    if (letrasDni[i].equals(substringLetra)) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    public static boolean validacioPass(String password) {
        String regex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{8,}$";
        return password.matches(regex);
    }
}