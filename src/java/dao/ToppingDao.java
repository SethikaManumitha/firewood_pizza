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

import model.Builder.Topping;
import util.JDBCUtils;
/**
 *
 * @author MAS
 */
public class ToppingDao {
    private final String SELECT_TOPPING_SQL = "SELECT * FROM topping";
    
     public List<Topping>  selectAllToppings() {
           List<Topping> toppings = new ArrayList<>();
            try (Connection connection = JDBCUtils.getInstance().getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TOPPING_SQL)) {

               System.out.println(preparedStatement);
            
               ResultSet rs = preparedStatement.executeQuery();
           
               while(rs.next()){
                    int id = rs.getInt("toppingid");
                    String name = rs.getString("toppingname");
                    int price = rs.getInt("price"); 
                    toppings.add(new Topping(id,name,price));
                }
            } catch (SQLException e) {
                printSQLException(e);
            }
            return toppings;
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
