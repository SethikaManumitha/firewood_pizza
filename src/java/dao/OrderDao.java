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
    
    private final String UPDATE_PIZZA_DEL_SQL = "UPDATE pizza SET pizzastatus = 2 WHERE pizzaname = ? AND custemail = ? ;";
    private final String SELECT_PIZZA_SQL = "SELECT * FROM Pizza WHERE pizzastatus = 0 AND custemail = ?";
    private final String SELECT_PIZZA_FAV_SQL = "SELECT * FROM pizza WHERE is_favorite = 1 AND  custemail = ? ;";
    private final String UPDATE_PIZZA_FAV_SQL = "UPDATE pizza SET pizzastatus = 0 WHERE pizzaname = ? AND custemail = ? ;";
    public List<Pizza>  selectAllPizza(String email) {
            
           List<Pizza> pizzaList = new ArrayList<>();
           
            try (Connection connection = JDBCUtils.getInstance().getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PIZZA_SQL)) {
                 preparedStatement.setString(1, email);
               System.out.println(preparedStatement);
            
               ResultSet rs = preparedStatement.executeQuery();
           
               while(rs.next()){
                    int id = rs.getInt("pizzaid");
                    String name = rs.getString("pizzaname");
                    String crust = rs.getString("crust");
                    String sauce = rs.getString("sauce"); 
                    String toppingString = rs.getString("topping"); 
                    
                    String[] toppings = toppingString.split(",");
                    
                    String size = rs.getString("size"); 
                    
                    String is_favourite = rs.getString("is_favorite"); 
                    String cheese = rs.getString("cheese"); 
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
    
     public void updatePizza(String name, String email) throws ClassNotFoundException {
    try (Connection connection = JDBCUtils.getInstance().getConnection();
         PreparedStatement updateStatement = connection.prepareStatement(UPDATE_PIZZA_FAV_SQL)) {
        
      
        updateStatement.setString(1, name);
        updateStatement.setString(2, email);
        updateStatement.executeUpdate();

        System.out.println("Successfully updated pizza status.");
    } catch (SQLException e) {
        printSQLException(e);
    }
}
     
      public void updatePizzaDel(String name, String email) throws ClassNotFoundException {
    try (Connection connection = JDBCUtils.getInstance().getConnection();
         PreparedStatement updateStatement = connection.prepareStatement(UPDATE_PIZZA_DEL_SQL)) {
        
      
        updateStatement.setString(1, name);
        updateStatement.setString(2, email);
        updateStatement.executeUpdate();

        System.out.println("Successfully deleted pizza status.");
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