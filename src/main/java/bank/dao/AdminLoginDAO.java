package bank.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminLoginDAO {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/ganesh";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Ganesh";

    static {
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public boolean validate(String username, String password) {
        boolean isValid = false;
        String query = "SELECT * FROM data WHERE username = ? AND password = ?";
        
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
             
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                isValid = resultSet.next();
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return isValid;
    }
}
