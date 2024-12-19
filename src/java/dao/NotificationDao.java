package dao;


import util.JDBCUtils;

import java.sql.*;
import java.util.*;
import model.Notification;
public class NotificationDao {

    private final String SELECT_NOTIFICATION_SQL = "SELECT * FROM notification WHERE email = ?";

    
    public List<Notification> getNotificationsByEmail(String email) {
        List<Notification> notifications = new ArrayList<>();

        try (Connection connection = JDBCUtils.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(SELECT_NOTIFICATION_SQL)) {

            
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            System.out.println(email);
            
            while (rs.next()) {
                String notificationMessage = rs.getString("notification");
                

                
                Notification notification = new Notification(email,notificationMessage);
                notifications.add(notification);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return notifications;
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
