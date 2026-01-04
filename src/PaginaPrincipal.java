import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaginaPrincipal extends JFrame {
    private JButton loginButton;
    private JPanel MainPage;
    private JButton registrarseButton;
    private JLabel labelMenu;

    public PaginaPrincipal() {

        setContentPane(MainPage);
        setTitle("Pàgina principal");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300,200);
        setLocationRelativeTo(null);
        setVisible(true);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PaginaPrincipal.this.dispose();
                new Login();

            }
        });
        registrarseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PaginaPrincipal.this.dispose();
                new Register();
            }
        });
    }
}
