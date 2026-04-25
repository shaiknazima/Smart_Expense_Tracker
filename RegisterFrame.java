package ui;

import dao.UserDAO;

import javax.swing.*;

public class RegisterFrame extends JFrame {

    public RegisterFrame() {

        setTitle("Register");
        setSize(300,250);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel l1 = new JLabel("Username:");
        l1.setBounds(30,30,100,25);
        add(l1);

        JTextField userField = new JTextField();
        userField.setBounds(120,30,120,25);
        add(userField);

        JLabel l2 = new JLabel("Password:");
        l2.setBounds(30,80,100,25);
        add(l2);

        JPasswordField passField = new JPasswordField();
        passField.setBounds(120,80,120,25);
        add(passField);

        JButton regBtn = new JButton("Register");
        regBtn.setBounds(90,140,120,30);
        add(regBtn);

        regBtn.addActionListener(e -> {
            UserDAO dao = new UserDAO();
            boolean success = dao.register(
                userField.getText(),
                new String(passField.getPassword())
            );

            if(success) {
                JOptionPane.showMessageDialog(this, "Registered Successfully");
                new LoginFrame();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Error");
            }
        });

        setVisible(true);
    }
}