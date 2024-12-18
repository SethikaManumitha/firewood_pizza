package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Customer;
import util.JDBCUtils;

public class CustomerDao {
    private final String INSERT_CUSTOMER_SQL = "INSERT INTO customer (full_name, email, pass, phone, address, dob, point) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?);";
    private final String SELECT_USERS_BY_EMAIL_SQL = "SELECT * FROM customer where email = ? AND pass= ?";

    
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
    
    // Check if customer exist in DB
    public boolean checkCustomer(String email, String password) {
            boolean exists = false;
            try (Connection connection = JDBCUtils.getInstance().getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USERS_BY_EMAIL_SQL)) {

                
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, password);

                System.out.println(preparedStatement); 
                ResultSet rs = preparedStatement.executeQuery();
                System.out.println(rs);
                
                exists = rs.next();
            } catch (SQLException e) {
                printSQLException(e);
            }
            return exists;
        }

    public Customer selectCustomer(String email, String password) {
    try (Connection connection = JDBCUtils.getInstance().getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USERS_BY_EMAIL_SQL)) {

        preparedStatement.setString(1, email);
        preparedStatement.setString(2, password);

        ResultSet rs = preparedStatement.executeQuery();

        // Make sure the result set contains rows
        if (rs.next()) {
            String name = rs.getString("full_name");
            String phone = rs.getString("phone");
            String address = rs.getString("address");
            Date dob = rs.getDate("dob"); 
            int point = rs.getInt("point");

           
            return new Customer(name, email, password, phone, address, dob, point);
        }
    } catch (SQLException e) {
        printSQLException(e);
    }
    return null;
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
