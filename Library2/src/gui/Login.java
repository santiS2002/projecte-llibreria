package gui;

import services.Manager;
import core.Person;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.management.MemoryNotificationInfo;

public class Login extends JFrame {
    private JLabel loginLabel;
    private JTextField mailField;
    private JTextField nameField;
    private JLabel mailLabel;
    private JLabel passLabel;
    private JButton loginButton;
    private JPanel Login;
    private JPasswordField passField;
    private int loginCounter = 0;
    private Manager manager;

    public Login(Manager manager) {
        this.manager = manager;

        setContentPane(Login);
        setTitle("User login");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300,200);
        setLocationRelativeTo(null);
        setVisible(true);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String mail = mailField.getText();
                String password = new String(passField.getPassword());

                Person userLoged = manager.login(mail,password);

                if(userLoged != null){
                    JOptionPane.showMessageDialog(null,"Correct login!");
                    Login.this.dispose();

                    new UserMenu(manager,mail);

                    loginCounter = 0;

                }else if (loginCounter >= 2) {
                        JOptionPane.showMessageDialog(null, "Too many attempts. The application will be locked for 5 seconds.");
                    manager.sendSecurityAlert(mail);

                    try {

                        Thread.sleep(5000);
                    } catch (Exception error) {
                        System.err.println("Error");
                    }
                    loginCounter = 0;
                }else{
                    JOptionPane.showMessageDialog(null,"Incorrect credentials");
                    loginCounter++;
                }

                mailField.setText("");
                passField.setText("");

            }
        });
    }


}
