/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.NotificationDao;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import model.Notification;
/**
 *
 * @author MAS
 */
@WebServlet("/notification")
public class NotificationServlet extends HttpServlet {

    private NotificationDao notificationDao;
    
    @Override
    public void init() throws ServletException {
        // Initialize the notificationDao object
        notificationDao = new NotificationDao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       doGet(request, response);
    }
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        showNotification(request, response);
    }

    private void showNotification(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String email = request.getParameter("email");
            System.out.println(email);
            List<Notification> notifications = notificationDao.getNotificationsByEmail(email);
            System.out.println(notifications);
            request.setAttribute("notification", notifications);
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("notification.jsp");
            dispatcher.forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(NotificationServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("errorMessage", "Failed to load notifications: " + ex.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
        }
    }
}

