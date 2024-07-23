package bank.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminCheckTransactionsDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/ganesh";
    private String dbUser = "root";
    private String dbPassword = "Ganesh";

    public AdminCheckTransactionsDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean isAccountExists(long accountNumber) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean exists = false;

        try {
            conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
            String sql = "SELECT * FROM user_details WHERE account_number = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, accountNumber);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                exists = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return exists;
    }

    public List<String> getAllTransactions(long accountNumber) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<String> transactions = new ArrayList<>();

        try {
            conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
            String sql = "SELECT * FROM transactions WHERE account_number = ? ORDER BY transaction_date DESC";
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, accountNumber);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                String transaction = String.format("%s: %s %s",
                        rs.getTimestamp("transaction_date"),
                        rs.getString("transaction_type").equals("deposit") ? "+" : "-",
                        rs.getDouble("transaction_amount"));
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return transactions;
    }
}
