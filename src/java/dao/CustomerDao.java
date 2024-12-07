package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Customer;
import util.JDBCUtils;

public class CustomerDao {
    private final String INSERT_CUSTOMER_SQL = "INSERT INTO customer (full_name, email, pass, phone, address, dob, point) VALUES (?, ?, ?, ?, ?, ?, ?);";
//    private final String SELECT_USERS_SQL = "SELECT * FROM customer";
//    private final String SELECT_USERS_BY_ID_SQL = "SELECT * FROM customer WHERE custid = ?";
//    private final String UPDATE_USERS_SQL = "UPDATE employee SET full_name = ?, email = ?, pass = ? WHERE id = ?;";
//    private final String DELETE_USERS_SQL = "DELETE FROM customer WHERE id = ?;";

    
    // Method to add employee
    public void addCustomer(Customer customer) throws ClassNotFoundException {
        
        try (Connection connection = JDBCUtils.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CUSTOMER_SQL)) {
            
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getEmail());
            preparedStatement.setString(3, customer.getPassword());
            preparedStatement.setString(4, customer.getPhone());
            preparedStatement.setString(5, customer.getAddress());
            preparedStatement.setDate(6, customer.getDob());
            preparedStatement.setInt(7, customer.getPoints());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            // Process SQL exception
            printSQLException(e);
        }
       
    }
    
    
    // Method to print SQL exception details
    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
