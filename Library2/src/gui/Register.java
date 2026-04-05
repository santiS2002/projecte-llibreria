package gui;

import services.Manager;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Register extends JFrame {


    private JLabel register;
    private JTextField dniTextField;
    private JTextField nameField;
    private JPanel registerPanel;
    private JLabel dniLabel;
    private JButton signButton;
    private JLabel nameLabel;
    private JTextField mailField;
    private JLabel mail;
    private JPasswordField passField;
    private JButton exit;
    private Manager manager;


    public Register(Manager manager) {
        this.manager = manager;

        setContentPane(registerPanel);
        setTitle("Sign up");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setVisible(true);


        signButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String dni = dniTextField.getText();
                String mail = mailField.getText();
                String password = new String(passField.getPassword());

                if (manager.register(mail, name, dni, password)) {
                    JOptionPane.showMessageDialog(null, "User registered successfully!");

                    dniTextField.setText("");
                    nameField.setText("");
                    mailField.setText("");
                    passField.setText("");

                    Register.this.dispose();
                    new Login(manager);

                } else {
                    JOptionPane.showMessageDialog(null,
                            "Registration failed. Please check:\n" +
                                    "- Correct DNI format\n" +
                                    "- Password (1 Upper, 1 Lower, 1 Number, min 8 chars)\n" +
                                    "- Email might already be in use");
                }

            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Register.this.dispose();
                new MainPage(manager);
            }
        });
    }


}