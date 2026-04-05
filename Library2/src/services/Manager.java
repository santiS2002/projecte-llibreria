package services;

import interfaces.IAdminActions;
import interfaces.IUserActions;
import interfaces.IVerification;
import core.Book;
import core.Library;
import core.Person;
import core.User;
import services.emails.EnviarEmails;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Manager implements IAdminActions, IUserActions, IVerification {
   private Library library;
   private ArrayList<Person> persons;
   private HashMap<String,ArrayList<Book>> borrowedBooks;

    public Manager(){
        persons = new ArrayList<>();
        borrowedBooks = new HashMap<>();
        library = new Library();
    }


    //email
    public void sendSecurityAlert(String mail) {
        String name = "User";

        for (Person p : persons) {
            if (p.getMail().equalsIgnoreCase(mail)) {
                name = p.getName();
                break;
            }
        }

        final String finalName = name;

        new Thread(() -> {
            try {
                EnviarEmails emailService = new EnviarEmails(mail, "Security Alert", "Too many attempts", finalName);
                emailService.enviarCorreuAlerta();
                System.out.println("Security alert sent to: " + mail);
            } catch (Exception e) {
                System.err.println("Error sending security email: " + e.getMessage());
            }
        }).start();
    }
    //interfaces.IUserActions

    @Override
    public boolean borrowBook(String mail, String bookTitle) {
        for(Map.Entry<Integer,Book>entry:library.getColeccioLlibres().entrySet()){
            Book book = entry.getValue();

            if(book.getTitle().equalsIgnoreCase(bookTitle) && book.isDisponible()){
                book.setDisponible(false);
                borrowedBooks.get(mail).add(book);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean returnBook(String mail, String bookTitle) {
        ArrayList<Book> userBooks = borrowedBooks.get(mail);

        for(int i=0;i<userBooks.size();i++){
            Book book = userBooks.get(i);

            if(book.getTitle().equalsIgnoreCase(bookTitle)){
                book.setDisponible(true);
                userBooks.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public  ArrayList<Book> seeBooksBorrowed(String mail) {
        ArrayList<Book> userBooks = borrowedBooks.get(mail);

        return userBooks;
    }

    @Override
    public ArrayList<Book> seeAvailableBooks() {
        ArrayList<Book> available = new ArrayList<>();
        for(Map.Entry<Integer,Book> entry:library.getColeccioLlibres().entrySet()){
            Book book = entry.getValue();

            if(book.isDisponible()){
                available.add(book);
            }
        }
        return available;
    }

    //interfaces.IUserActions.IAdminActions
    @Override
    public boolean addBook(Book book) {
        if(library.getColeccioLlibres().containsKey(book.getId())){
            return false;
        }
        library.getColeccioLlibres().put(book.getId(),book);
        return true;
    }

    public boolean deleteBook(int id){
        if(library.getColeccioLlibres().containsKey(id)){
            library.getColeccioLlibres().remove(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean register(String mail, String name, String dni, String password) {
        if(!calculDni(dni)){
            return false;
        }

            for(Person person:persons){
                if(person.getMail().equalsIgnoreCase(mail)){
                    return false;

                }
            }

            if(!validacioPass(password)){
                return false;
            }

            User newUser = new User(mail,name,dni,password);
            borrowedBooks.put(newUser.getMail(),new ArrayList<>());

            persons.add(newUser);

        final String finalName = name;
        final String finalMail = mail;

        new Thread(() -> {
            try {
                EnviarEmails welcomeEmail = new EnviarEmails(finalMail, "Welcome", "Registration", finalName);
                welcomeEmail.enviarEmailBenvinguda();
            } catch (Exception e) {
                System.err.println("Could not send welcome email");
            }
        }).start();
        return true;
    }

    @Override
    public Person login(String mail, String password) {
        for(Person person:persons){
            if(person.getMail().equalsIgnoreCase(mail) && person.getPassword().equals(password)){
                return person;
            }
        }
        return null;

    }

    public static boolean calculDni(String numDni){ // usuari fica x pantalla

        try{

            if(numDni == null || numDni.length() != 9)return false;

            String[] letrasDni ={"T","R","W","A","G","M","Y","F","P",
                    "D","X","B","N","J","Z","S","Q","V",
                    "H","L","C","K","E"};

            String substringDni = numDni.substring(0, 8); // retalla les numeros del string
            String substringLetra = numDni.substring(8, 9); //retalla la lletra de la pos final.
            int dniEnter = Integer.parseInt(substringDni); //passar a int els nums en string d'abans


            for(int i = 0; i < letrasDni.length; i++) {

                if (dniEnter % 23 == i) {
                    if (letrasDni[i].equals(substringLetra)) {
                        return true;
                    }

                }
            }
        }catch (Exception e){

            return false;
        }

        return false;
    }

    public static boolean validacioPass(String password){

        String regex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{8,}$";

        return password.matches(regex);
    }




    }





