package bank.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import bank.model.CreateAccountforUser;

public class EditAccountDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/ganesh";
    private String dbUser = "root";
    private String dbPassword = "Ganesh";

    public EditAccountDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public CreateAccountforUser getAccountDetails(String accountNumber) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        CreateAccountforUser accountDetails = null;

        try {
            conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
            String sql = "SELECT name, address, mobile, email, dob, balance, account_type, account_status FROM user_details WHERE account_number = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, accountNumber);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                String address = rs.getString("address");
                String mobile = rs.getString("mobile");
                String email = rs.getString("email");
                String dob = rs.getString("dob");
                double balance = rs.getDouble("balance");
                String accountType = rs.getString("account_type");
                String accountStatus = rs.getString("account_status");

                accountDetails = new CreateAccountforUser(name, address, mobile, email, dob, balance, "", accountNumber, accountType, accountStatus);
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

        return accountDetails;
    }

    public boolean saveEditedAccountDetails(CreateAccountforUser accountDetails) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean success = false;

        try {
            conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
            String sql = "UPDATE user_details SET name = ?, address = ?, mobile = ?, email = ?, dob = ?, account_type = ?, account_status = ? WHERE account_number = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, accountDetails.getName());
            pstmt.setString(2, accountDetails.getAddress());
            pstmt.setString(3, accountDetails.getMobile());
            pstmt.setString(4, accountDetails.getEmail());
            pstmt.setString(5, accountDetails.getDob());
            pstmt.setString(6, accountDetails.getAccountType());
            pstmt.setString(7, accountDetails.getAccountStatus());
            pstmt.setString(8, accountDetails.getAccountNumber());

            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                success = true;
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

        return success;
    }
}
