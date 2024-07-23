package bank.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bank.model.CreateAccountforUser;

public class ViewAccountDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/ganesh";
    private String dbUser = "root";
    private String dbPassword = "Ganesh";

    public ViewAccountDAO() {
        // Initialize database connection
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public CreateAccountforUser getAccountDetails(String accountNumber) {
        String sql = "SELECT name, address, mobile, email, dob, balance, account_type, account_status, created_date FROM user_details WHERE account_number = ?";
        try (Connection conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, accountNumber);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    // Retrieve data from ResultSet
                    String name = rs.getString("name");
                    String address = rs.getString("address");
                    String mobile = rs.getString("mobile");
                    String email = rs.getString("email");
                    String dob = rs.getString("dob");
                    double balance = rs.getDouble("balance");
                    String password = ""; // You might need to handle this separately
                    String accountType = rs.getString("account_type");
                    String accountStatus = rs.getString("account_status");
                    String createdDate = rs.getString("created_date");

                    // Create instance of CreateAccountforUser with retrieved data
                    return new CreateAccountforUser(name, address, mobile, email, dob, balance, password, accountNumber, accountType, accountStatus, createdDate);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
