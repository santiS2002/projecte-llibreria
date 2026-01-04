import dades.Dades;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LlibresPrestats extends JFrame {
    private JPanel LlistaPrestats;
    private JList<String> llistat;
    private JButton sortir;
    private String correu;

    public LlibresPrestats (String correu) {
        this.correu = correu;
        setContentPane(LlistaPrestats);
        DefaultListModel<String> model = new DefaultListModel<>();
        ArrayList<String> llibres = Dades.prestecsFets.get(correu);

        if(llibres != null){
            for(String titol: llibres){
                model.addElement(titol);
            }
        }

        llistat.setModel(model);
        setTitle("Llista Prestats");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300,200);
        setLocationRelativeTo(null);
        setVisible(true);


        sortir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                LlibresPrestats.this.dispose();
                new UserMenu(correu);
            }
        });
    }
}
