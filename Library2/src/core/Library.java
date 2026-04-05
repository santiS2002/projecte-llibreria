package core;

import java.util.HashMap;

public class Library {
    private HashMap<Integer, Book> coleccioLlibres; //llibres disponibles

    public Library() {
        coleccioLlibres = new HashMap<>();
    }

    public HashMap<Integer, Book> getColeccioLlibres() {
        return coleccioLlibres;
    }

    public void setColeccioLlibres(HashMap<Integer, Book> coleccioLlibres) {
        this.coleccioLlibres = coleccioLlibres;
    }
}
