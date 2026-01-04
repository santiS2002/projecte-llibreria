package dades;
import java.util.*;

/*1-missatge user-friendly user,2.login-dni(EXPLICAR),maxim intents,3.guardar correu, enviant correu, intents d'entrar fallits,4.static amb usuaris guardats,tractament strings*/


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.


public class Dades {

    public static HashMap<String, String[]> dadesUsuari = new HashMap<>(); //guarda dni i noms d'usuari
    public static HashMap<String,String[]> coleccioLlibres = new HashMap<>(); //llibres disponibles
    public static HashMap<String,String[]> coleccioLlibresPermanents;
    public static ArrayList<String> llistaLlibresDisponibles = new ArrayList<>(); //guarda els llibres q hi ha a la biblio sense prestar encara.
    public static HashMap<String, ArrayList<String>> prestecsFets = new HashMap<>(); //key dni, value és l'array de llibres prestats de cada usuari(cada usuari té un array)
    public static ArrayList<String> llistaPrestatsUsuari; // la llista iniciada.
    // BLOQUE ESTÁTICO: Se ejecuta al cargar la clase en memoria
    static {

        dadesUsuari.put("21754557F@iespoblenou.org",new String[]{"Santi","21754557F","Contrasenya1"});//añadimos usuario estaticamente
        coleccioLlibres.put("El Quijote", new String[]{"Cervantes", "Novela", "disponible"});
        coleccioLlibres.put("1984", new String[]{"George Orwell", "Distopía", "disponible"});
        coleccioLlibres.put("El Hobbit", new String[]{"Tolkien", "Fantasía", "disponible"});
        coleccioLlibresPermanents = new HashMap<>(coleccioLlibres);//coleccio on no s'esborren els llibres prestats
        prestecsFets.put("21754557F@iespoblenou.org",llistaPrestatsUsuari = new ArrayList<>());


    }


   
}
