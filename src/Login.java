import dades.Dades;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {
    private JLabel loginLabel;
    private JTextField correuField;
    private JTextField nomField;
    private JLabel correuLabel;
    private JLabel passLabel;
    private JButton loginButton;
    private JPanel Login;
    private JPasswordField passField;
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

                String correu = correuField.getText();
                String password = new String(passField.getPassword());

                if(Dades.dadesUsuari.containsKey(correu) && Dades.dadesUsuari.get(correu)[2].equals(password)){

                    JOptionPane.showMessageDialog(null,"Login correcte!");
                    Login.this.dispose();
                    new UserMenu(correu);

                    contadorLogin = 0;

                }else if (contadorLogin > 2) {
                        JOptionPane.showMessageDialog(null, "Massa intents. L'aplicació es bloquejarà 5 segons.");

                        if(Dades.dadesUsuari.containsKey(correu)){
                            MailServei.enviarCorreuAlerta(correu,Dades.dadesUsuari.get(correu)[0]);

                        }

                        try {
                            Thread.sleep(5000); // 5 segons de pausa
                        } catch (Exception error) {
                        }

                        contadorLogin = 0;
                }else{
                    JOptionPane.showMessageDialog(null,"Credencials incorrectes");
                    contadorLogin++;
                }

                correuField.setText("");
                nomField.setText("");

            }
        });
    }


}
