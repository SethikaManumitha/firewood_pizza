package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import util.JDBCUtils;

public class FavoriteDao {

    private final String SELECT_FEEDBACK_SQL = "SELECT * FROM feedback";

    public List<HashMap<String, String>> selectFeedback() throws ClassNotFoundException {

        try (Connection connection = JDBCUtils.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FEEDBACK_SQL)) {

            List<HashMap<String, String>> feedbackList = new ArrayList<>();
            System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Iterate over all rows in the result set
            while (resultSet.next()) {
                // Retrieve data for each row
                String feedbackText = resultSet.getString("feedback");
                String rating = resultSet.getString("rating");
                String user = resultSet.getString("fuser");
                String order = resultSet.getString("orderID");

                // Split the `orderID` field if it contains multiple pizzas
                String[] pizzas = order.split(",");

                for (String pizza : pizzas) {
                    HashMap<String, String> feedbackMap = new HashMap<>();

                    // Extract only the pizza name (remove anything after '=')
                    pizza = pizza.trim();
                    

                    // Populate feedback details
                    feedbackMap.put("item", pizza);
                    feedbackMap.put("feedback", feedbackText);
                    feedbackMap.put("rating", rating);
                    feedbackMap.put("user", user);

                    // Add the feedback map to the list
                    feedbackList.add(feedbackMap);
                }
            }

            return feedbackList;

        } catch (SQLException e) {
            printSQLException(e);
        }

        return null;
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
