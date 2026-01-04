import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import dades.Dades;

public class agafarLlibre extends JFrame {

    private JTextField nomLlibre;
    private JPanel agafarLlibre;
    private JButton Agafar;
    private JLabel llibreDesitjat;
    private JButton sortirButton;
    private String correu;

    public agafarLlibre (String correu) {
       this.correu = correu;
        setContentPane(agafarLlibre);
        setTitle("Menú d'usuari");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300,200);
        setLocationRelativeTo(null);
        setVisible(true);
        Agafar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String nom = nomLlibre.getText();

                String[] llibre = Dades.coleccioLlibres.get(nom);

                if(Dades.coleccioLlibres.containsKey(nom)){
                    Dades.prestecsFets.get(correu).add(nom);
                    JOptionPane.showMessageDialog(null,"Llibre agafat correctament");
                    Dades.coleccioLlibres.remove(nom);


                }else{

                    JOptionPane.showMessageDialog(null,"Llibre no disponible");
                }


                }
        });
        sortirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                agafarLlibre.this.dispose();
                new UserMenu(correu);
            }
        });
    }
}
