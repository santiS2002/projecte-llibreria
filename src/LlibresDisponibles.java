import dades.Dades;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LlibresDisponibles extends JFrame {
    private JList llista;
    private JPanel llistaDisponibles;
    private JButton sortirButton;
    private String correu;

    public LlibresDisponibles(String correu) {
        this.correu = correu;
        setContentPane(llistaDisponibles);

        DefaultListModel<String> model = new DefaultListModel<>();
        ArrayList<String> disponibles = new ArrayList<>(Dades.coleccioLlibres.keySet());

        if(disponibles != null){

            for(String titol: disponibles){

                model.addElement(titol);
            }
        }

        llista.setModel(model);
        setTitle("Llibres disponibles");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300,200);
        setLocationRelativeTo(null);
        setVisible(true);

        sortirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            LlibresDisponibles.this.dispose();
            new UserMenu(correu);
            }
        });
    }
}
