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
    private final String INSERT_PIZZA_SQL = "INSERT INTO Pizza (pizzaname, custemail, crust, sauce, topping, size,  pizzastatus,is_favorite,cheese)\n" +
"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
    private final String SELECT_PIZZA_SQL = "SELECT * FROM Pizza WHERE pizzastatus = 0 AND custemail = ?";
    
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
        
        preparedStatement.executeUpdate();
        System.out.println("Success");
    } catch (SQLException e) {
        // Process SQL exception
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
                    String name = rs.getString("pizzaname");
                    String crust = rs.getString("crust");
                    String sauce = rs.getString("sauce"); 
                    String toppingString = rs.getString("topping"); 
                    
                    String[] toppings = toppingString.split(",");
                    
                    String size = rs.getString("size"); 
                    
                    String is_favourite = rs.getString("is_favorite"); 
                    String cheese = rs.getString("cheese"); 
                    
                    Pizza pizza = new Pizza.Builder()
                    .setName(name)
                    .setCrust(crust)
                    .setSauce(sauce)
                    .setSize(size)
                    .addToppings(toppings)
                    .includeCheese(true)
                    .setIsFavourite(true)
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