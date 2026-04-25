package dao;

import db.DBConnection;
import model.Expense;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExpenseDAO {

    // ✅ Add Expense
    public void addExpense(Expense e) {
        try {
            Connection con = DBConnection.getConnection();

            String query = "INSERT INTO expenses(user_id, amount, category, date, note) VALUES(?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, e.getUserId());
            ps.setDouble(2, e.getAmount());
            ps.setString(3, e.getCategory());
            ps.setDate(4, e.getDate());
            ps.setString(5, e.getNote());

            ps.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // ✅ Get Expenses as List (optional use)
    public List<Expense> getExpenses(int userId) {
        List<Expense> list = new ArrayList<>();

        try {
            Connection con = DBConnection.getConnection();

            String query = "SELECT * FROM expenses WHERE user_id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Expense e = new Expense();

                e.setId(rs.getInt("id"));
                e.setUserId(rs.getInt("user_id"));
                e.setAmount(rs.getDouble("amount"));
                e.setCategory(rs.getString("category"));
                e.setDate(rs.getDate("date"));
                e.setNote(rs.getString("note"));

                list.add(e);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // ✅ Get Expenses as ResultSet (USED FOR JTable)
    public ResultSet getExpensesResultSet(int userId) {
        try {
            Connection con = DBConnection.getConnection();

            String query = "SELECT amount, category, date, note FROM expenses WHERE user_id=?";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, userId);

            return ps.executeQuery();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // ✅ Delete Expense
    public void deleteExpense(int id) {
        try {
            Connection con = DBConnection.getConnection();

            String query = "DELETE FROM expenses WHERE id=?";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, id);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}