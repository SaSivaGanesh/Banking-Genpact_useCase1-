package bank.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO {
    private final String jdbcURL = "jdbc:mysql://localhost:3306/ganesh";
    private final String dbUser = "root";
    private final String dbPassword = "Ganesh";

    // Method to check if an account exists in user_details table
    public boolean isAccountExists(long accountNumber) {
        boolean accountExists = false;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
            String sql = "SELECT COUNT(*) FROM user_details WHERE account_number = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, accountNumber);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                if (count > 0) {
                    accountExists = true;
                }
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

        return accountExists;
    }

    // Method to check if an account has transactions in transactions table
    public boolean hasTransactions(long accountNumber) {
        boolean hasTransactions = false;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
            String sql = "SELECT COUNT(*) FROM transactions WHERE account_number = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, accountNumber);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                if (count > 0) {
                    hasTransactions = true;
                }
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

        return hasTransactions;
    }

    // Method to fetch last 10 transactions for an account number
    public List<String> getLast10Transactions(long accountNumber) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<String> transactions = new ArrayList<>();

        try {
            conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
            String sql = "SELECT * FROM transactions WHERE account_number = ? ORDER BY transaction_date DESC LIMIT 10";
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
