import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import dades.Dades;

public class retornarLlibre extends JFrame{
    private JTextField llibreRetornar;
    private JPanel RetornarLlibre;
    private JButton retornar;
    private JLabel nomLlibre;
    private JButton sortirButton;
    private String correu;

    public retornarLlibre (String correu) {
        this.correu = correu;
        setContentPane(RetornarLlibre);
        setTitle("Retornar llibre");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300,200);
        setLocationRelativeTo(null);
        setVisible(true);
        retornar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String nom = llibreRetornar.getText();

                if(Dades.prestecsFets.get(correu).contains(nom)){

                    Dades.prestecsFets.get(correu).remove(nom);
                    JOptionPane.showMessageDialog(null,nom+" retornat correctament!");

                    Dades.coleccioLlibres.put(nom,Dades.coleccioLlibresPermanents.get(nom));

                }else{
                    JOptionPane.showMessageDialog(null,"No tens aquest llibre");
                }
            }
        });
        sortirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                retornarLlibre.this.dispose();
                new UserMenu(correu);
            }
        });
    }
}
