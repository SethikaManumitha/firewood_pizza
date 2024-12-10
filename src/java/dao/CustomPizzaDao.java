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

import model.Pizza;
import model.Crust;
import model.Sauce;
import model.Topping;
import util.JDBCUtils;

public class CustomPizzaDao {
    private final String INSERT_PIZZA_SQL = "INSERT INTO Pizza (pizzaname, custemail, crust, sauce, topping, cheese, size, is_favorite, price)\n" +
"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
    
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
        preparedStatement.setBoolean(6, pizza.isIncludeCheese());
        preparedStatement.setString(7, pizza.getSize());
        preparedStatement.setBoolean(8, pizza.isFavourite());
        preparedStatement.setInt(9, pizza.getPrice());
        preparedStatement.executeUpdate();
        System.out.println("Success");
    } catch (SQLException e) {
        // Process SQL exception
        printSQLException(e);
    }
}


    private void printSQLException(SQLException e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
