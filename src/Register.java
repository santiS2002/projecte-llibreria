import dades.Dades;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class Register extends JFrame {


    private JLabel Registre;
    private JTextField dniTextField;
    private JTextField nomField;
    private JPanel Register;
    private JLabel DniLabel;
    private JButton SignButton;
    private JLabel NomLabel;


    public Register () {
        setContentPane(Register);
        setTitle("Registre usuari");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300,200);
        setLocationRelativeTo(null);
        setVisible(true);



        SignButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nom = nomField.getText();
                String dni = dniTextField.getText();


                if(!calculDni(dni)){

                    JOptionPane.showMessageDialog(null,"el dni és incorrecte, intenta-ho de nou.");

                }else {


                    if (Dades.dadesUsuari.containsKey(dni)) {

                        JOptionPane.showMessageDialog(null, "Aquest usuari ja existeix!");

                    } else {
                        Dades.dadesUsuari.put(dni,nom);
                        Dades.prestecsFets.put(dni,Dades.llistaPrestatsUsuari = new ArrayList<>());
                        JOptionPane.showMessageDialog(null,"Usuari registrat correctament!");
                    }
                }

                dniTextField.setText("");
                nomField.setText("");
            }
        });
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





    public static void main(String[] args){

        new Register();
    }
}
