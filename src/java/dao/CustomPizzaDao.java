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

import model.Customer;
import util.JDBCUtils;

/**
 *
 * @author MAS
 */
public class CustomPizzaDao {
    private final String SELECT_CRUST_SQL = "SELECT * FROM crust";
    private final String SELECT_SAUCE_SQL = "SELECT * FROM sauce";
    private final String SELECT_TOPPING_SQL = "SELECT * FROM topping";
    
    
  
}
