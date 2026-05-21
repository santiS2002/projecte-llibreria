package presentation;


import gui.MainPage;
import services.LibrarySystem;

public class Main {
    public static void main(String[] args) {
        LibrarySystem controller = new LibrarySystem();

        new MainPage(controller);
    }
}