package core;

public class Book {
    private int id;
    private String title;
    private String author;
    private String genre;
    private boolean disponible;

    public Book(int id, String title, String author, String genre, boolean disponible) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.disponible = disponible;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    @Override
    public String toString() {
        String estado = disponible ? "Disponible" : "Prestat";
        return String.format("ID: %d | %-20s | %-15s | [%s]", id, title, author, estado);
    }
}
