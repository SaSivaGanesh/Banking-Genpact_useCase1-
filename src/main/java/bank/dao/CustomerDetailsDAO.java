package bank.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import bank.model.Customer;

public class CustomerDetailsDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/ganesh";
    private String dbUser = "root";
    private String dbPassword = "Ganesh";

    public CustomerDetailsDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Customer getCustomerDetails(long accountNumber) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Customer customer = null;

        try {
            conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);

            String sql = "SELECT name, address, dob, mobile_number, email, account_number, account_type FROM user_details WHERE account_number = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, accountNumber);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                String address = rs.getString("address");
                String dob = rs.getString("dob");
                String mobileNumber = rs.getString("mobile_number");
                String email = rs.getString("email");
                long accountNum = rs.getLong("account_number");
                String accountType = rs.getString("account_type");

                customer = new Customer(name, address, dob, mobileNumber, email, accountNum, accountType);
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

        return customer;
    }
}
