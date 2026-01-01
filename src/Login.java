import dades.Dades;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {
    private JLabel loginLabel;
    private JTextField dniField;
    private JTextField nomField;
    private JLabel dniLabel;
    private JLabel nomLabel;
    private JButton loginButton;
    private JPanel Login;
    private int contadorLogin = 0;

    public Login() {
        setContentPane(Login);
        setTitle("Login d'usuari");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300,200);
        setLocationRelativeTo(null);
        setVisible(true);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String dni = dniField.getText();
                String nom = nomField.getText();

                if(Dades.dadesUsuari.containsKey(dni) && Dades.dadesUsuari.get(dni).equals(nom)){

                    JOptionPane.showMessageDialog(null,"Login correcte!");
                    Login.this.dispose();
                    new UserMenu(dni);

                    contadorLogin = 0;

                }else if (contadorLogin >= 3) {
                        JOptionPane.showMessageDialog(null, "Massa intents. L'aplicació es bloquejarà 5 segons.");

                        try {
                            Thread.sleep(5000); // 5 segons de pausa
                        } catch (Exception error) {
                        }

                        contadorLogin = 0;
                }else{
                    JOptionPane.showMessageDialog(null,"Credencials incorrectes");
                    contadorLogin++;
                }

                dniField.setText("");
                nomField.setText("");

            }
        });
    }

    public static void main(String[] args) {
        new Login();
    }
}
