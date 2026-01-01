import io.github.cdimascio.dotenv.Dotenv;

import java.util.*;
/*1-missatge user-friendly user,2.login-dni(EXPLICAR),maxim intents,3.guardar correu, enviant correu, intents d'entrar fallits,4.static amb usuaris guardats,tractament strings*/


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.


public class Main {


    public static boolean calculDni(String numDni){ // usuari fica x pantalla

        String[] letrasDni ={"T","R","W","A","G","M","Y","F","P",
                "D","X","B","N","J","Z","S","Q","V",
                "H","L","C","K","E"};

        String substringDni = numDni.substring(0, 8); // retalla les numeros del string
        String substringLetra = numDni.substring(8, 9); //retalla la lletra de la pos final.
        int dniEnter = Integer.parseInt(substringDni); //passar a int els nums en string d'abans

        for(int i = 0; i < letrasDni.length; i++){

            if(dniEnter%23 == i){
                if(letrasDni[i].equals(substringLetra)){
                    return true;
                }
               // System.out.println(substringDni + letrasDni[i]);
            }


        }
        return false;
    }

    public static void testRegistre() {
    System.out.println("=== INICIANT TEST DE REGISTRE I ENVIAMENT ===");

    String nomTest = "Usuari Prova";
    String correuTest = "enebamailxd@gmail.com"; // Posa el teu correu per veure si t'arriba!

    System.out.println("Provant enviament de correu a: " + correuTest);

//    String assumpte = "Benvingut/da a la Biblioteca SCRUM!";
//    String missatge = assumpte + "\nHola " + nomTest + ",\n\nGràcies per registrar-te.";

    MailServei.enviarCorreuBenvinguda(correuTest, nomTest);

    System.out.println("=== TEST FINALITZAT ===");
}

public static void ferDiagnosticConfiguracio() {
    Dotenv dotenv = Dotenv.load();
    String sgKey = dotenv.get("API_KEY");

    System.out.println("\n--- DIAGNÒSTIC SENDGRID ---");
    if (sgKey == null || sgKey.isEmpty()) {
        System.err.println(" ERROR: No s'ha trobat API_KEY al .env");
    } else {
        System.out.println(" API_KEY trobada.");
    }
    System.out.println("---------------------------\n");
}

    public static final String USER_MENU = "OPCIONS LLIBRERIA\n"+
            "Agafar llibre:1\n"+
            "retornar llibre:2\n"+
            "veure llibres prestats:3\n"+
            "llibres disponibles:4\n"+
            "sortir:5";//variable estatica

    public static final String LIBRARY_MENU = "OPCIONS USUARI\n"+
            "Registre usuari:1\n"+
            "Login usuari:2\n"+
            "Eliminar usuari:3\n"+
            "Sortir:4\n";//variable estatica

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

//        testRegistre();
//        ferDiagnosticConfiguracio();

        HashMap<String,String[]> coleccioLlibres = new HashMap<>();

        coleccioLlibres.put("El Quijote", new String[]{"Cervantes", "Novela", "disponible"});
        coleccioLlibres.put("1984", new String[]{"George Orwell", "Distopía", "disponible"});
        coleccioLlibres.put("El Hobbit", new String[]{"Tolkien", "Fantasía", "disponible"});


        HashMap<String,String[]> coleccioLlibresPermanents = new HashMap<>(coleccioLlibres);


        // usuaris en un diccionari, dni com a key, value el nom
        // biblioteca gestionada amb arrayLists

        HashMap<String, String[]> dadesUsuari = new HashMap<>(); //guarda dni i noms d'usuari

        ArrayList<String> llistaLlibresPrestatsTotals = new ArrayList<>(); //guarda tots els llibres prestats de la biblioteca.
        ArrayList<String> llistaLlibresDisponibles = new ArrayList<>(); //guarda els llibres q hi ha a la biblio sense prestar encara.*/

        HashMap<String, ArrayList<String>> prestecsFets = new HashMap<>(); //key dni, value és l'array de llibres prestats de cada usuari(cada usuari té un array)
        ArrayList<String> llistaPrestatsUsuari; // la llista iniciada.

        //el value que és un arrayList, representa l'array de llibres prestats, ja que l'usuari vol veure els llibres prestats que té.
        //detall:  no pot ser l'array general de prestats ja que serien TOTS ELS LLIBRES de la biblio i això no especifica quins llibres prestats té cada usuari...
        // Cada usuari tindrà quantitat diferent de llibres prestats, dades privades.
        //això significa, que cada vegada que es dóna d'alta un usuari, s'ha de crear un arrayList nou per llibres prestats. així, cada usuari tindrà array de llibres prestats independents.
        //com que seràn values, es podrà accedir a cada array segons la key del hash map principal q té les dades de tots els usuaris.
        //perquè? perquè la key és el dni, per tant, si el dni de l'usuari coincideix amb la key del map de llibres prestats que és dni també, mostrarà el value específic d'aquella key(usuari) i obtindrem l'array segons dni.

        //MENÚ USUARIS
        //afegir usuari: (crearà un arraylist de llibres prestats per cada usuari)
        //donar-se de baixa:(eliminar usuari i també l'array de prestats individual segons el dni esborrat!)
        // opcio on login fet:, accedir al menu de la biblio. Aqui si l'usuari ha entrat a la biblio i ha demanat llibres prestats, la propera vegada que s'entri al menu, hauria d'emplenar-se l'array dellibres prestats específics.
         //quants préstecs té un usuari-: (veure segons dni quins llibres tinc a l'array. És a dir, accedir al hash map prestectsFets segons dni(key) per obtenir value.)
        //sortir del menú:


        System.out.println("Benvingut a la llibreria, pulsa 1 per accedir al menú de l'usuari");
        int eleccion = sc.nextInt();

        if(eleccion == 1) {
          //  testRegistre();
          //  ferDiagnosticConfiguracio();

            int opcioUsuaris = 0;
            int contadorLogin = 0; // comptador fora perque sino en el bucle es reinicia quan es fa un intent fallit!

            while (opcioUsuaris != 4) {


                System.out.println(LIBRARY_MENU);

                opcioUsuaris = sc.nextInt();

                sc.nextLine();

                switch (opcioUsuaris) {

                    case 1: //afegir un usuari

                        System.out.println("Introdueix les teves dades per a donar-te d'alta a la web Biblioteca!");

                        System.out.print("Dni: ");

                        String dniUsuari = sc.nextLine();

                        if(!calculDni(dniUsuari)){

                            System.out.println("el dni és incorrecte, intenta-ho de nou.");
                            break;

                        }

                        System.out.print("Nom: ");
                        String nomUsuari = sc.nextLine();

                        System.out.println("Correu: ");
                        String correuUsuari = sc.nextLine();

                        System.out.println("Contrasenya: ");
                        String contrasenyaUsuari = sc.nextLine();

                        if (dadesUsuari.containsKey(dniUsuari)) {

                            System.out.println("Aquest dni ja existeix a la base de dades! Has d'introduir un altre dni.");

                            return;
                        }

                        if(dniUsuari.length() != 9 ){

                            System.out.println("Format de dni incorrecte");

                            break;
                        }

                        dadesUsuari.put(dniUsuari, new String[]{nomUsuari, correuUsuari, contrasenyaUsuari});

                        prestecsFets.put(dniUsuari, new ArrayList<>()); //això fica un arraylist buit com a value. s'ha d'emplenar només si l'usuari demana llibres prestats.

                        System.out.println("Enviant correu de benvinguda a " + correuUsuari + "...");
                        MailServei.enviarCorreuBenvinguda(correuUsuari, nomUsuari);
                        System.out.println("Usuari registrat correctament!");

                        break;

                    case 2:

                        System.out.print("Dni: ");
                        dniUsuari = sc.nextLine();

                        System.out.print("Nom: ");
                        nomUsuari = sc.nextLine();

                        System.out.println("Contrasenya: ");
                        contrasenyaUsuari = sc.nextLine();


                        if(dadesUsuari.containsKey(dniUsuari) && dadesUsuari.get(dniUsuari)[2].equals(contrasenyaUsuari)){
                            //com get dni retorna value i value es un array, [0] torna el nomUsuari en pos 0!
                            System.out.println("Login correcte!\n");
                            int accio = 0;


                            while(accio != 5){

                                System.out.println(USER_MENU);

                                accio = sc.nextInt();

                                sc.nextLine();

                                switch(accio){

                                    case 1:

                                        System.out.println("Escull un llibre disponible");

                                        String llibreEscollit = sc.nextLine();

                                        String[] llibre = coleccioLlibres.get(llibreEscollit);
                                        if(coleccioLlibres.containsKey(llibreEscollit) && llibre[2].equals("disponible")){

                                             prestecsFets.get(dniUsuari).add(llibreEscollit);

                                            System.out.println("Llibre agafat correctament");

                                            coleccioLlibres.remove(llibreEscollit);


                                            break;


                                        }else{

                                            System.out.println("LLibre no disponible");

                                            break;
                                        }

                                    case 2:

                                        System.out.println("Quin llibre vols retornar");

                                        llibreEscollit = sc.nextLine();


                                        if(prestecsFets.get(dniUsuari).contains(llibreEscollit)){

                                            prestecsFets.get(dniUsuari).remove(llibreEscollit);
                                            System.out.println(llibreEscollit+ " retornat correctament.");

                                            coleccioLlibres.put(llibreEscollit,coleccioLlibresPermanents.get(llibreEscollit));

                                            break;

                                        }else{

                                            System.out.println("No tens aquest llibre");

                                            break;
                                        }

                                    case 3:


                                        if (prestecsFets.isEmpty()) {

                                            System.out.println("L'usuari"+nomUsuari+" no té prestecs fets.");

                                            return;

                                        }

                                        for (Map.Entry<String, ArrayList<String>> entradaDades : prestecsFets.entrySet()) {
                                            String dni = entradaDades.getKey();

                                            ArrayList<String> llistaLlibresUsuari = entradaDades.getValue();

                                            if(llistaLlibresUsuari.isEmpty()){

                                                System.out.println("No tens cap llibre");

                                                break;

                                            }else{

                                                System.out.println("----llistat de llibres prestats per l'usuari " + nomUsuari + ", amb el dni: " + dni + " :----");

                                                System.out.println(llistaLlibresUsuari);

                                                break;
                                            }




                                        }
                                        break;


                                    case 4:

                                        if(!coleccioLlibres.isEmpty()){

                                            System.out.println("Llibres disponibles:");

                                            for(String llibreDisponible : coleccioLlibres.keySet()){

                                                System.out.println(llibreDisponible);
                                            }
                                            break;

                                        }else{

                                            System.out.println("No hi ha cap llibre disponible actualment");
                                        }


                                }



                            }
                            System.out.println("Sessió tancada");


                            break;

                        }
                        else{
                            contadorLogin++;
                            System.out.println("Error, contrasenya incorrecta. Intent " + contadorLogin + " de 3. Si supera 3 intents es bloquejarà el compte.");
                             if(contadorLogin >= 3){
                                System.out.println("Has superat els intents màxims!");

                                if(dadesUsuari.containsKey(dniUsuari)){
                                    String emailEnviar = dadesUsuari.get(dniUsuari)[1]; //com q el value es un array, [1] obté la dada correu de l'usuari.
                                    MailServei.enviarCorreuAlerta(emailEnviar, nomUsuari);
                                    try {
                                        Thread.sleep(30000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    contadorLogin = 0;
                                }
                                //POner contador tiempo para permitir volver a intentar el login

                            }


                            break;
                        }



                    case 3: // treure un usuari

                        System.out.println("introdueix el teu dni i el teu nom d'usuari per donar-te de baixa del sistema: ");

                        dniUsuari = sc.nextLine();

                        nomUsuari = sc.nextLine();

                        if (!dadesUsuari.containsKey(dniUsuari) && !dadesUsuari.get(dniUsuari)[0].equals(nomUsuari)) {

                            System.out.println("No es poden eliminar les dades del sistema! Usuari no existeix. ");

                            return;

                        }else {

                            System.out.println("s'estàn eliminant les dades de l'usuari " + nomUsuari + " amb el DNI : " + dniUsuari);

                            dadesUsuari.remove(dniUsuari, nomUsuari);
                            prestecsFets.remove(dniUsuari);

                            System.out.println("dades eliminades correctament!");
                        }

                        break;

                }

            }
        }else if(eleccion == 2){


            String[] llibreAfegit = new String[3];


            int opcioLlibre = sc.nextInt();
            sc.nextLine();

            while (opcioLlibre != 6) {



                switch (opcioLlibre) {

                    case 1:


                        String nomLlibre = sc.nextLine();

                        if (nomLlibre.isEmpty()) {

                            System.out.println("Error");

                            break;
                        } else {

                            System.out.println("Author:");

                            String autor = sc.nextLine();

                            System.out.println("Genre:");

                            String genero = sc.nextLine();

                            llistaLlibresDisponibles.add(nomLlibre);

                            llibreAfegit[0] = autor;

                            llibreAfegit[1] = genero;

                            llibreAfegit[2] = "disponible";

                            coleccioLlibres.put(nomLlibre, llibreAfegit);

                            System.out.println("Libro añadido correctamente");

                            break;






                        }




                }


                opcioLlibre = sc.nextInt();

                sc.nextLine();

            }


        }

        }
}
