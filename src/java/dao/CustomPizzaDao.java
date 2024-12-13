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

public class CustomPizzaDao {
    private final String INSERT_PIZZA_SQL = "INSERT INTO Pizza (pizzaname, custemail, crust, sauce, topping, size,  pizzastatus,is_favorite,cheese,price,qty)\n" +
"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?,?,?);";
    private final String SELECT_PIZZA_SQL = "SELECT * FROM Pizza WHERE pizzastatus = 0 AND custemail = ?";
    private final String DELETE_PIZZA_SQL = "DELETE FROM Pizza WHERE pizzaname = ? AND custemail = ?";
    
    private final String SELECT_PIZZA_FAV_SQL = "SELECT * FROM pizza WHERE pizzaname = ? AND custemail = ? ;";
    private final String UPDATE_PIZZA_SQL = "UPDATE pizza SET is_favorite = ? WHERE pizzaname = ? AND custemail = ?;";
    
   public void insertPizza(Pizza pizza, String email) throws ClassNotFoundException {
    try (Connection connection = JDBCUtils.getInstance().getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PIZZA_SQL)) {

        preparedStatement.setString(1, pizza.getName());
        preparedStatement.setString(2, email);
        preparedStatement.setString(3, pizza.getCrust());
        preparedStatement.setString(4, pizza.getSauce());

        
        String toppings = "";
        List<String> toppingsList = pizza.getToppings();
        for (int i = 0; i < toppingsList.size(); i++) {
        toppings += toppingsList.get(i); 
        if (i < toppingsList.size() - 1) {
            toppings += ","; 
            }
        }

        preparedStatement.setString(5, toppings);
        
        preparedStatement.setString(6, pizza.getSize());
       
        preparedStatement.setBoolean(7, pizza.isFavourite());
        preparedStatement.setBoolean(8, pizza.isIncludeCheese());
        preparedStatement.setInt(9, 0);
        preparedStatement.setDouble(10, pizza.getPrice());
        preparedStatement.setInt(11, pizza.getQty());
        
        preparedStatement.executeUpdate();
        System.out.println("Success");
    } catch (SQLException e) {
        // Process SQL exception
        printSQLException(e);
    }
}
   
   public void deletePizza(String name,String email) throws ClassNotFoundException {
    try (Connection connection = JDBCUtils.getInstance().getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PIZZA_SQL)) {

        preparedStatement.setString(1, name);
         preparedStatement.setString(2, email);
        preparedStatement.executeUpdate();
        System.out.println("Successfully deleted");
    } catch (SQLException e) {
        printSQLException(e);
    }
}
 public void updatePizza(String name, String email) throws ClassNotFoundException {
    try (Connection connection = JDBCUtils.getInstance().getConnection();
         PreparedStatement selectStatement = connection.prepareStatement(SELECT_PIZZA_FAV_SQL);
         PreparedStatement updateStatement = connection.prepareStatement(UPDATE_PIZZA_SQL)) {

        
        String is_favorite = "0";
        selectStatement.setString(1, name);
        selectStatement.setString(2, email);
        ResultSet rs = selectStatement.executeQuery();

        if (rs.next()) {
            is_favorite = rs.getString("is_favorite");
        }

        
        is_favorite = "0".equals(is_favorite) ? "1" : "0";

        updateStatement.setString(1, is_favorite);
        updateStatement.setString(2, name);
        updateStatement.setString(3, email);
        updateStatement.executeUpdate();

        System.out.println("Successfully updated favorite status.");
    } catch (SQLException e) {
        printSQLException(e);
    }
}

   
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