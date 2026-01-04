import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserMenu extends JFrame{
    private JPanel userMenu;
    private JLabel userTitle;
    private JButton llibresDisponibles;
    private JButton llibresPrestats;
    private JButton retornarLlibre;
    private JButton agafarLlibre;
    private JButton sortir;
    private String correu;

    public UserMenu (String correu){
        this.correu = correu;
        setContentPane(userMenu);
        setTitle("Menú d'usuari");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300,300);
        setLocationRelativeTo(null);
        setVisible(true);
        agafarLlibre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                UserMenu.this.dispose();
                new agafarLlibre(correu);

            }
        });
        retornarLlibre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserMenu.this.dispose();
                new retornarLlibre(correu);
            }
        });
        llibresPrestats.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                UserMenu.this.dispose();
                new LlibresPrestats(correu);
            }
        });
        llibresDisponibles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserMenu.this.dispose();
                new LlibresDisponibles(correu);
            }
        });
        sortir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserMenu.this.dispose();
                new PaginaPrincipal();
            }
        });
    }
}
