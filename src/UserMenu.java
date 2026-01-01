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
    private String dni;

    public UserMenu (String dni){
        this.dni = dni;
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
                new agafarLlibre(dni);

            }
        });
        retornarLlibre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserMenu.this.dispose();
                new retornarLlibre(dni);
            }
        });
        llibresPrestats.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                UserMenu.this.dispose();
                new LlibresPrestats(dni);
            }
        });
        llibresDisponibles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserMenu.this.dispose();
                new LlibresDisponibles(dni);
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
