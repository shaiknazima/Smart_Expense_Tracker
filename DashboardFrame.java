package ui;

import dao.ExpenseDAO;
import model.Expense;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class DashboardFrame extends JFrame {

    private int userId;
    private JTable table;
    private DefaultTableModel model;

    public DashboardFrame(int userId) {
        this.userId = userId;

        setTitle("Dashboard");
        setSize(600,400);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel l1 = new JLabel("Amount:");
        l1.setBounds(30,20,100,25);
        add(l1);

        JTextField amountField = new JTextField();
        amountField.setBounds(100,20,120,25);
        add(amountField);

        JLabel l2 = new JLabel("Category:");
        l2.setBounds(250,20,100,25);
        add(l2);

        JTextField categoryField = new JTextField();
        categoryField.setBounds(330,20,120,25);
        add(categoryField);

        JButton addBtn = new JButton("Add");
        addBtn.setBounds(470,20,80,25);
        add(addBtn);

        // Table
        model = new DefaultTableModel();
        table = new JTable(model);

        model.addColumn("Amount");
        model.addColumn("Category");
        model.addColumn("Date");
        model.addColumn("Note");

        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(30,70,520,250);
        add(pane);

        // Load data initially
        loadExpenses();

        // Add button action
        addBtn.addActionListener(e -> {
            try {
                Expense ex = new Expense();
                ex.setUserId(userId);
                ex.setAmount(Double.parseDouble(amountField.getText()));
                ex.setCategory(categoryField.getText());
                ex.setDate(new Date(System.currentTimeMillis()));
                ex.setNote("Added");

                new ExpenseDAO().addExpense(ex);

                JOptionPane.showMessageDialog(this, "Expense Added");

                // Clear fields
                amountField.setText("");
                categoryField.setText("");

                // Refresh table
                loadExpenses();

            } catch(Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid Input");
            }
        });

        setVisible(true);
    }

    // Method to load data into table
    private void loadExpenses() {
        try {
            model.setRowCount(0); // clear table

            ExpenseDAO dao = new ExpenseDAO();
            ResultSet rs = dao.getExpensesResultSet(userId);

            while(rs.next()) {
                model.addRow(new Object[]{
                    rs.getDouble("amount"),
                    rs.getString("category"),
                    rs.getDate("date"),
                    rs.getString("note")
                });
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}