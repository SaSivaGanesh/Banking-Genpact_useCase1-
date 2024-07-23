package bank.dao;

import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import bank.model.Customer;
import bank.model.PasswordUtil;

public class CustomerLoginDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/ganesh";
    private String dbUser = "root";
    private String dbPassword = "Ganesh";

    public CustomerLoginDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    

    public Customer getUserDetails(long accountNumber) {
        Customer customer = null;
        String sql = "SELECT * FROM user_details WHERE account_number = ?";

        try (Connection conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, accountNumber);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    customer = new Customer(
                            rs.getString("name"),
                            rs.getString("address"),
                            rs.getString("dob"),
                            rs.getString("mobile"),
                            rs.getString("email"),
                            rs.getLong("account_number"),
                            rs.getString("account_type")
                    );
                    customer.setBalance(rs.getDouble("balance"));
                    customer.setAccountStatus(rs.getString("account_status"));
                    customer.setCreatedDate(rs.getDate("created_date"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customer;
    }
    
    public boolean validateUser(long accountNumber, String username, String inputPassword) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean isValidUser = false;

        try {
            conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);

            String sql = "SELECT password FROM user_details WHERE account_number = ? AND name = ? AND account_status = 'open'";
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, accountNumber);
            pstmt.setString(2, username);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                String storedPassword = rs.getString("password");

                // Check if the stored password is encrypted
                if (PasswordUtil.isEncrypted(storedPassword)) {
                    // Decrypt the stored password
                    String decryptedPassword = PasswordUtil.decrypt(storedPassword);
                    // Validate against decrypted password
                    isValidUser = decryptedPassword.equals(inputPassword);
                } else {
                    // Validate against stored unencrypted password
                    isValidUser = storedPassword.equals(inputPassword);
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

        return isValidUser;
    }
    public boolean updatePassword(long accountNumber, String newPassword) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean isUpdated = false;

        try {
            // Encrypt the new password before saving
            String encryptedPassword = PasswordUtil.encrypt(newPassword);

            conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
            String sql = "UPDATE user_details SET password = ? WHERE account_number = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, encryptedPassword);
            pstmt.setLong(2, accountNumber);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                isUpdated = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return isUpdated;
    }

    public double getBalance(long accountNumber) {
        double balance = 0.00;
        String sql = "SELECT balance FROM user_details WHERE account_number = ?";

        try (Connection conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, accountNumber);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    balance = rs.getDouble("balance");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle SQL exceptions
        }

        return balance;
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
        
        public boolean isWithdrawalAllowed(long accountNumber) {
            boolean isAllowed = false;
            Connection conn = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;

            try {
                conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
                String sql = "SELECT created_date FROM user_details WHERE account_number = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setLong(1, accountNumber);
                rs = pstmt.executeQuery();

                if (rs.next()) {
                    Date createdDate = rs.getDate("created_date");
                    if (createdDate != null) {
                        LocalDate currentDate = LocalDate.now(ZoneId.of("Asia/Kolkata"));
                        LocalDate createdLocalDate = createdDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                        
                        // Example condition: Allow withdrawal if current date is after created date
                        isAllowed = currentDate.isAfter(createdLocalDate);
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

            return isAllowed;
        }


        public String getAccountStatus(long accountNumber) {
            Connection conn = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            String accountStatus = "";

            try {
                conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
                String sql = "SELECT account_status FROM user_details WHERE account_number = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setLong(1, accountNumber);

                rs = pstmt.executeQuery();
                if (rs.next()) {
                    accountStatus = rs.getString("account_status");
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

            return accountStatus;
        }

       
        
        


        public void logTransaction(long accountNumber, double amount, String type) {
            Connection conn = null;
            PreparedStatement pstmt = null;

            try {
                conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
                String sql = "INSERT INTO transactions (account_number, transaction_amount, transaction_type, transaction_date) VALUES (?, ?, ?, CURRENT_TIMESTAMP)";
                pstmt = conn.prepareStatement(sql);
                pstmt.setLong(1, accountNumber);
                pstmt.setDouble(2, amount);
                pstmt.setString(3, type);

                pstmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (pstmt != null) pstmt.close();
                    if (conn != null) conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        public boolean updateBalance(long accountNumber, double amount) {
            Connection conn = null;
            PreparedStatement pstmt = null;
            boolean isBalanceUpdated = false;

            try {
                conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);

                double currentBalance = getBalance(accountNumber);

                if (currentBalance + amount < 0) {
                    return false; // Negative balance is not allowed
                }

                String sql = "UPDATE user_details SET balance = ? WHERE account_number = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setDouble(1, currentBalance + amount);
                pstmt.setLong(2, accountNumber);

                int rowsUpdated = pstmt.executeUpdate();
                if (rowsUpdated > 0) {
                    // Log the transaction
                    logTransaction(accountNumber, amount, amount >= 0 ? "deposit" : "withdraw");
                    isBalanceUpdated = true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (pstmt != null) pstmt.close();
                    if (conn != null) conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            return isBalanceUpdated;
        }
        
       
        public String getAccountType(long accountNumber) {
            String accountType = null;
            String sql = "SELECT account_type FROM user_details WHERE account_number = ?";

            try (Connection conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setLong(1, accountNumber);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        accountType = rs.getString("account_type");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return accountType;
        }
        public Date getCreatedDate(long accountNumber) {
            Connection conn = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            Date createdDate = null;

            try {
                conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
                String sql = "SELECT created_date FROM user_details WHERE account_number = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setLong(1, accountNumber);

                rs = pstmt.executeQuery();
                if (rs.next()) {
                    createdDate = rs.getDate("created_date");
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

            return createdDate;
        }


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
        

        public boolean closeAccount(long accountNumber) {
            Connection conn = null;
            PreparedStatement pstmt = null;
            boolean isClosed = false;

            try {
                conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
                
                // Step 1: Check if the balance is 0.00
                String sqlCheckBalance = "SELECT balance FROM user_details WHERE account_number = ?";
                pstmt = conn.prepareStatement(sqlCheckBalance);
                pstmt.setLong(1, accountNumber);

                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    double balance = rs.getDouble("balance");
                    if (balance == 0.00) {
                        // Step 2: Update account_status to 'closed' if balance is 0.00
                        String sqlUpdateStatus = "UPDATE user_details SET account_status = 'closed' WHERE account_number = ?";
                        pstmt = conn.prepareStatement(sqlUpdateStatus);
                        pstmt.setLong(1, accountNumber);

                        int rowsUpdated = pstmt.executeUpdate();
                        if (rowsUpdated > 0) {
                            isClosed = true;
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (pstmt != null) pstmt.close();
                    if (conn != null) conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            return isClosed;
        }



}
