/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author MAS
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Builder.*;

import util.JDBCUtils;

public class OrderDao {
    private final String INSERT_ORDER_SQL = "INSERT INTO ordertbl (custemail, items, address, delivery_option, payment_type, total,discount, status,date) \n" +
"VALUES (?, ?, ?, ?, ?, ?, ?,?,?);";
    private final String UPDATE_PIZZA_DEL_SQL = "UPDATE pizza SET pizzastatus = 2 WHERE pizzaname = ? AND custemail = ? ;";
    private final String SELECT_PIZZA_SQL = "SELECT * FROM Pizza WHERE pizzastatus = 0 AND custemail = ?";
    private final String SELECT_PIZZA_FAV_SQL = "SELECT * FROM pizza WHERE is_favorite = 1 AND  custemail = ? ;";
    private final String UPDATE_PIZZA_FAV_SQL = "UPDATE pizza SET pizzastatus = 0 WHERE pizzaname = ? AND custemail = ? ;";
    private final String UPDATE_PIZZA_ORDER_SQL = "UPDATE pizza SET pizzastatus = 1 WHERE pizzaname = ? AND custemail = ? ;";
    private final String SELECT_POINTS_SQL = "SELECT * FROM customer WHERE email = ?";
    private final String UPDATE_POINTS_SQL = "UPDATE customer SET point = ? WHERE email = ?";
    public List<Pizza>  selectAllPizza(String email) {
            
           List<Pizza> pizzaList = new ArrayList<>();
           
            try (Connection connection = JDBCUtils.getInstance().getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PIZZA_SQL)) {
                 preparedStatement.setString(1, email);
               System.out.println(preparedStatement);
            
               ResultSet rs = preparedStatement.executeQuery();
           
               while(rs.next()){
                   
                    String name = rs.getString("pizzaname");
                    String crust = rs.getString("crust");
                    String sauce = rs.getString("sauce"); 
                    String toppingString = rs.getString("topping"); 
                    
                    String[] toppings = toppingString.split(",");
                    
                    String size = rs.getString("size"); 
                    

                    float price = rs.getFloat("price");
                    int qty = rs.getInt("qty");
                    
                    Pizza pizza = new Pizza.Builder()
                    .setName(name)
                    .setCrust(crust)
                    .setSauce(sauce)
                    .setSize(size)
                    .addToppings(toppings)
                    .includeCheese(true)
                    .setIsFavourite(true)
                    .setPrice(price)
                    .setQty(qty)
                    .build();
                    
                    pizzaList.add(pizza);
                }
            } catch (SQLException e) {
                printSQLException(e);
            }
            return pizzaList;
        }
    
    //Update pizza status to 0
     public void updatePizza(String name, String email) throws ClassNotFoundException {
    try (Connection connection = JDBCUtils.getInstance().getConnection();
         PreparedStatement updateStatement = connection.prepareStatement(UPDATE_PIZZA_FAV_SQL)) {
    
        updateStatement.setString(1, name);
        updateStatement.setString(2, email);
        updateStatement.executeUpdate();
        
    } catch (SQLException e) {
        printSQLException(e);
    }
}
     // Select Loyalty Points
     public int  selectPoints(String email) {
            
           int points = 0;
            try (Connection connection = JDBCUtils.getInstance().getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(SELECT_POINTS_SQL)) {
                 
               preparedStatement.setString(1, email);
               System.out.println(preparedStatement);
            
               ResultSet rs = preparedStatement.executeQuery();
           
               while(rs.next()){
                    points = rs.getInt("point");
               
                }
            } catch (SQLException e) {
                printSQLException(e);
            }
            return points;
        }
     
    // Update loyalty points
    public void updatePoints(int points, String email) throws ClassNotFoundException {
    try (Connection connection = JDBCUtils.getInstance().getConnection();
         PreparedStatement updateStatement = connection.prepareStatement(UPDATE_POINTS_SQL)) {
        
      
        updateStatement.setInt(1, points);
        updateStatement.setString(2, email);
        updateStatement.executeUpdate();

        System.out.println("Successfully updated pizza status.");
    } catch (SQLException e) {
        printSQLException(e);
    }
}
     // Update pizza status to 2
      public void removePizza(String name, String email) throws ClassNotFoundException {
    try (Connection connection = JDBCUtils.getInstance().getConnection();
         PreparedStatement updateStatement = connection.prepareStatement(UPDATE_PIZZA_DEL_SQL)) {
        
      
        updateStatement.setString(1, name);
        updateStatement.setString(2, email);
        updateStatement.executeUpdate();

    } catch (SQLException e) {
        printSQLException(e);
    }
}
    
      // Insert order
      public void insertOrder(Order order, String email) throws ClassNotFoundException {
    try (Connection connection = JDBCUtils.getInstance().getConnection();
         PreparedStatement insertStatement = connection.prepareStatement(INSERT_ORDER_SQL);
         PreparedStatement updateStatement = connection.prepareStatement(UPDATE_PIZZA_ORDER_SQL)) {

        
        insertStatement.setString(1, email);
        insertStatement.setString(2, order.getItems().toString());  
        insertStatement.setString(3, order.getAddress());
        insertStatement.setString(4, order.getDeliveryOption());
        insertStatement.setString(5, order.getPaymentType());
        insertStatement.setDouble(6, order.getTotal());
        insertStatement.setDouble(7, order.getDiscount());
        insertStatement.setString(8, order.getStatus());
        insertStatement.setDate(9, new java.sql.Date(order.getDate().getTime()));
        insertStatement.executeUpdate();

        System.out.println("Successfully inserted the order.");

        
        for (String pizzaName : order.getItems().keySet()) {
           
            updateStatement.setString(1, pizzaName); 
            updateStatement.setString(2, email);     
            updateStatement.executeUpdate();        
        }

        System.out.println("Successfully updated pizza status for the order.");

    } catch (SQLException e) {
        printSQLException(e);
    }
}

      public List<Pizza>  selectFavPizza(String email) {
            
           List<Pizza> pizzaList = new ArrayList<>();
           
            try (Connection connection = JDBCUtils.getInstance().getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PIZZA_FAV_SQL)) {
                 preparedStatement.setString(1, email);
               System.out.println(preparedStatement);
            
               ResultSet rs = preparedStatement.executeQuery();
           
               while(rs.next()){
                    //int id = rs.getInt("pizzaid");
                    String name = rs.getString("pizzaname");
                    String crust = rs.getString("crust");
                    String sauce = rs.getString("sauce"); 
                    String toppingString = rs.getString("topping"); 
                    
                    String[] toppings = toppingString.split(",");
                    
                    String size = rs.getString("size"); 
                    float price = rs.getFloat("price");
                    int qty = rs.getInt("qty");
                    Pizza pizza = new Pizza.Builder()
                    .setName(name)
                    .setCrust(crust)
                    .setSauce(sauce)
                    .setSize(size)
                    .addToppings(toppings)
                    .includeCheese(true)
                    .setIsFavourite(true)
                    .setPrice(price)
                    .setQty(qty)
                    .build();
                    
                    pizzaList.add(pizza);
                }
            } catch (SQLException e) {
                printSQLException(e);
            }
            return pizzaList;
        }


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