import services.Manager;
import gui.MainPage;
import core.Book;

public class Main {
    public static void main(String[] args) {
        Manager manager = new Manager();


        manager.addBook(new Book(1, "The Great Gatsby", "F. Scott Fitzgerald", "Classic", 1));
        manager.addBook(new Book(2, "1984", "George Orwell", "Dystopian", 1));
        manager.addBook(new Book(3, "The Hobbit", "J.R.R. Tolkien", "Fantasy", 1));


        new MainPage(manager);

        System.out.println("Library System is running...");
    }
}