package ui;

import dao.UserDAO;

import javax.swing.*;
import java.awt.event.*;

public class LoginFrame extends JFrame {

    private JTextField userField;
    private JPasswordField passField;

    public LoginFrame() {

        setTitle("Login");
        setSize(350,250);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Username Label
        JLabel l1 = new JLabel("Username:");
        l1.setBounds(30,30,100,25);
        add(l1);

        // Username Field
        userField = new JTextField();
        userField.setBounds(140,30,150,25);
        add(userField);

        // Password Label
        JLabel l2 = new JLabel("Password:");
        l2.setBounds(30,80,100,25);
        add(l2);

        // Password Field
        passField = new JPasswordField();
        passField.setBounds(140,80,150,25);
        add(passField);

        // Login Button
        JButton loginBtn = new JButton("Login");
        loginBtn.setBounds(50,140,100,30);
        add(loginBtn);

        // Register Button
        JButton regBtn = new JButton("Register");
        regBtn.setBounds(180,140,100,30);
        add(regBtn);

        // Login Action
        loginBtn.addActionListener(e -> {
            String username = userField.getText();
            String password = new String(passField.getPassword());

            UserDAO dao = new UserDAO();
            int userId = dao.login(username, password);

            if(userId != -1) {
                JOptionPane.showMessageDialog(this, "Login Successful");

                new DashboardFrame(userId);
                dispose(); // close login window

            } else {
                JOptionPane.showMessageDialog(this, "Invalid Username or Password");
            }
        });

        // Register Action
        regBtn.addActionListener(e -> {
            new RegisterFrame();
            dispose(); // close login window
        });

        setVisible(true);
    }
}