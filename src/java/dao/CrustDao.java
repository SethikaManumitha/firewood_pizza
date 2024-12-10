/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Crust;
import util.JDBCUtils;
/**
 *
 * @author MAS
 */
public class CrustDao {
    private final String SELECT_CRUST_SQL = "SELECT * FROM crust";
    
     public List<Crust>  selectAllCrust() {
           List<Crust> crusts = new ArrayList<>();
            try (Connection connection = JDBCUtils.getInstance().getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CRUST_SQL)) {

               System.out.println(preparedStatement);
            
               ResultSet rs = preparedStatement.executeQuery();
           
               while(rs.next()){
                    int id = rs.getInt("crustid");
                    String name = rs.getString("crustname");
                    int price = rs.getInt("price"); 
                    crusts.add(new Crust(id,name,price));
                }
            } catch (SQLException e) {
                printSQLException(e);
            }
            return crusts;
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
