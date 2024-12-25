package dao;

import model.Builder.Order;
import util.JDBCUtils;

import java.sql.*;
import java.util.*;
import model.Builder.OrderBuilder;
import model.Topping;

public class AdminDao {

    private final String SELECT_ORDER_SQL = "SELECT * FROM ordertbl WHERE status = ?";
    private final String SELECT_PIZZA_SQL = "SELECT * FROM pizza WHERE custemail = ? AND pizzaname = ?";
    private final String SELECT_CUSTOMER_SQL = "SELECT * FROM customer WHERE email = ?";
    private final String INSERT_NOTIFICATION_SQL = "INSERT INTO notification (email, notification) VALUES (?, ?)";
    private final String INSERT_PROMO_SQL = "INSERT INTO promotion (descript, price,discount) VALUES (?, ?,?)";
    private final String UPDATE_STATE_SQL = "UPDATE ordertbl SET status = ? WHERE id = ?";
    
    private final String UPDATE_TOPPING_SQL = "UPDATE topping SET discount = ? WHERE (`toppingid` = ?);";
    private final String SELECT_TOPPING_SQL = "SELECT * FROM topping";

    private final String SELECT_ORDER_COUNT_SQL = "SELECT COUNT(*) FROM ordertbl WHERE status = ?";

    // Select all orders related to a certain state
    public List<Order> selectAllOrders(String state) {
        List<Order> orderList = new ArrayList<>();

        try (Connection connection = JDBCUtils.getInstance().getConnection();
             // Retrieve related order records
             PreparedStatement orderStmt = connection.prepareStatement(SELECT_ORDER_SQL);
             // Retrieve related customer details
             PreparedStatement customerStmt = connection.prepareStatement(SELECT_CUSTOMER_SQL);
             // Retrieve order item details
             PreparedStatement pizzaStmt = connection.prepareStatement(SELECT_PIZZA_SQL)) {

            orderStmt.setString(1, state);
            
            // Retrieve all the order results
            ResultSet rs = orderStmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String email = rs.getString("custemail");
                String items = rs.getString("items");
                String address = rs.getString("address");
                String deliveryOption = rs.getString("delivery_option");
                double total = rs.getDouble("total");
                double discount = rs.getDouble("discount");
                java.sql.Date date = rs.getDate("date");

                // Fetch customer name
                String customerName = "";
                customerStmt.setString(1, email);
                ResultSet customerResult = customerStmt.executeQuery();
                if (customerResult.next()) {
                    customerName = customerResult.getString("full_name");
                }

                // Parse order details into a hash map to enter into the order object
                Map<String, String> pizzaItems = new HashMap<>();
                items = items.substring(1, items.length() - 1);
                String[] itemsList = items.split(",");
                
                for (String item : itemsList) {
                    String[] parts = item.split("=");
                    if (parts.length == 2) {
                        String pizzaName = parts[0].trim();

                        pizzaStmt.setString(1, email);
                        pizzaStmt.setString(2, pizzaName);
                        ResultSet pizzaResult = pizzaStmt.executeQuery();

                        String pizzaDetails = "";
                        if (pizzaResult.next()) {
                            String size = pizzaResult.getString("size");
                            String crust = pizzaResult.getString("crust");
                            String sauce = pizzaResult.getString("sauce");
                            String topping = pizzaResult.getString("topping");

                            // Convert the order detail into a readable format 
                            pizzaDetails = size + " sized pizza with " + crust + ", " + sauce + ", and " + topping;
                        }
                        pizzaItems.put(pizzaName, pizzaDetails);
                    }
                }

                // Create Order object
                
                 Order order = new OrderBuilder(id, customerName, email)
                    .items(pizzaItems)
                    .address(address)
                    .deliveryOption(deliveryOption)
                    .total(total)
                    .discount(discount)
                    .status(state)
                    .date(date)
                    .build();

                orderList.add(order);
                
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return orderList;
    }

    // Select all toppings related to a certain state
    public List<Topping> selectAllToppings() {
        List<Topping> toppings = new ArrayList<>();
        try (Connection connection = JDBCUtils.getInstance().getConnection();
             PreparedStatement toppingStmt = connection.prepareStatement(SELECT_TOPPING_SQL)) {

            try (ResultSet rs = toppingStmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("toppingid");
                    String name = rs.getString("toppingname");
                    double price = rs.getDouble("price");
                    double discount = rs.getDouble("discount");
                    // Create a Topping object and add it to the list
                    Topping topping = new Topping(id, name, price,discount);
                    toppings.add(topping);
                }
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return toppings; 
    }


    // select the order coutn
    public int selectOrderCount(String state) {
        int count = 0;

        try (Connection connection = JDBCUtils.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(SELECT_ORDER_COUNT_SQL)) {

            stmt.setString(1, state);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return count;
    }

    // update the order status
    public void updateOrder(int id, String state) throws ClassNotFoundException {
        try (Connection connection = JDBCUtils.getInstance().getConnection();
             PreparedStatement updateStatement = connection.prepareStatement(UPDATE_STATE_SQL)) {

            updateStatement.setString(1, state);
            updateStatement.setInt(2, id);
            updateStatement.executeUpdate();

           
        } catch (SQLException e) {
            printSQLException(e);
        }
    }
    
    // update the topping price
    public void updateToppingPrice(int id,double discount) throws ClassNotFoundException {
        try (Connection connection = JDBCUtils.getInstance().getConnection();
             PreparedStatement updateStatement = connection.prepareStatement(UPDATE_TOPPING_SQL)) {

            
            updateStatement.setDouble(1, discount);
            updateStatement.setInt(2, id);
            updateStatement.executeUpdate();

           
        } catch (SQLException e) {
            printSQLException(e);
        }
    }
    // insert notifiation to database
    public void insertNotification(String email, String notification) throws ClassNotFoundException {
    try (Connection connection = JDBCUtils.getInstance().getConnection();
         PreparedStatement insertStatement = connection.prepareStatement(INSERT_NOTIFICATION_SQL)) {

        insertStatement.setString(1, email);
        insertStatement.setString(2, notification);
        insertStatement.executeUpdate();

    } catch (SQLException e) {
        printSQLException(e);
    }
}
    
    // insert notifiation to database
    public void insertPromotion(String descript, double total, double discount) throws ClassNotFoundException {
    try (Connection connection = JDBCUtils.getInstance().getConnection();
         PreparedStatement insertStatement = connection.prepareStatement(INSERT_PROMO_SQL)) {

        insertStatement.setString(1, descript);
        insertStatement.setDouble(2, total);
        insertStatement.setDouble(3, discount);
        insertStatement.executeUpdate();

    } catch (SQLException e) {
        printSQLException(e);
    }
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
                    System.err.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
