package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Feedback;
import util.JDBCUtils;


public class FeedbackDao {

    private final String INSERT_FEEDBACK_SQL = "INSERT INTO feedback (orderID, feedback, rating, fuser, fstatus) VALUES (?, ?, ?, ?, ?);";
    private final String UPDATE_FEEDBACK_SQL = "UPDATE feedback SET fstatus = ? WHERE orderID = ?;";
    private final String SELECT_FEEDBACK_BY_ORDER_USER_SQL = "SELECT * FROM feedback WHERE orderID = ? AND fuser = ?;";

   
    //Insert feedback into the database
    public void insertFeedback(Feedback feedback) throws ClassNotFoundException {
        try (Connection connection = JDBCUtils.getInstance().getConnection();
             PreparedStatement insertStatement = connection.prepareStatement(INSERT_FEEDBACK_SQL)) {

            insertStatement.setString(1, feedback.getId());
            insertStatement.setString(2, feedback.getFeedback());
            insertStatement.setString(3, feedback.getRating());
            insertStatement.setString(4, feedback.getUser());
            insertStatement.setString(5, feedback.getStatus());
            insertStatement.executeUpdate();

            System.out.println("Successfully inserted the feedback.");

        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public void updateFeedback(String status,String feedbackId) throws ClassNotFoundException {
        try (Connection connection = JDBCUtils.getInstance().getConnection();
             PreparedStatement updateStatement = connection.prepareStatement(UPDATE_FEEDBACK_SQL)) {

            updateStatement.setString(1, status);
            updateStatement.setString(2, feedbackId);
            updateStatement.executeUpdate();
            System.out.println(updateStatement);
            System.out.println("Successfully updated the feedback status.");

        } catch (SQLException e) {
            printSQLException(e);
        }
    }
    
    public Feedback selectFeedback(String orderId, String username) throws ClassNotFoundException {
        
        try (Connection connection = JDBCUtils.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FEEDBACK_BY_ORDER_USER_SQL)) {

            
            preparedStatement.setString(1, orderId);
            preparedStatement.setString(2, username);

            System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
               
                String feedbackText = resultSet.getString("feedback");
                String rating = resultSet.getString("rating");
                String user = resultSet.getString("fuser");
                String order = resultSet.getString("orderID");
                
                Feedback feedback  = new Feedback(order,feedbackText,rating,user);
                return feedback;
            }
           
            
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
