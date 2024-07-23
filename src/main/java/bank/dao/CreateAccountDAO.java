package bank.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

import bank.model.CreateAccountforUser;

public class CreateAccountDAO {

    public String addUser(CreateAccountforUser user, String createdDate) {
        Connection con = null;
        PreparedStatement ps = null;
        String accountNumber = null;

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish a connection
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ganesh", "root", "Ganesh");

            // Generate a unique 10-digit account number
            accountNumber = generateAccountNumber();

            // Prepare SQL query
            if (createdDate == null || createdDate.isEmpty()) {
                ps = con.prepareStatement("INSERT INTO user_details (name, address, mobile, email, dob, account_number, balance, password, account_type) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
                ps.setString(1, user.getName());
                ps.setString(2, user.getAddress());
                ps.setString(3, user.getMobile());
                ps.setString(4, user.getEmail());
                ps.setString(5, user.getDob());
                ps.setString(6, accountNumber);
                ps.setDouble(7, user.getBalance());
                ps.setString(8, user.getPassword());
                ps.setString(9, user.getAccountType()); // New field
            } else {
                ps = con.prepareStatement("INSERT INTO user_details (name, address, mobile, email, dob, account_number, balance, password, account_type, created_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                ps.setString(1, user.getName());
                ps.setString(2, user.getAddress());
                ps.setString(3, user.getMobile());
                ps.setString(4, user.getEmail());
                ps.setString(5, user.getDob());
                ps.setString(6, accountNumber);
                ps.setDouble(7, user.getBalance());
                ps.setString(8, user.getPassword());
                ps.setString(9, user.getAccountType()); // New field
                ps.setString(10, createdDate);
            }

            // Execute update
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return accountNumber; // Return the generated account number
    }

    private String generateAccountNumber() {
        Random random = new Random();
        StringBuilder accountNumber = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            accountNumber.append(random.nextInt(10));
        }
        return accountNumber.toString();
    }
}
