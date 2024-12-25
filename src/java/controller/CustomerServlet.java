package controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CustomerDao;
import dao.FavoriteDao;
import java.util.HashMap;
import java.util.List;
import model.Customer;
import model.Feedback;

/**
 * @author MAS
 */
@WebServlet("/")
public class CustomerServlet extends HttpServlet {

    private CustomerDao customerDao;
    private FavoriteDao favoirteDao;

    public void init() {
        customerDao = new CustomerDao();
        favoirteDao = new FavoriteDao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        switch (action) {
            case "/new":
                showNewForm(request, response);
                break;
            case "/insert":
                try {
                    insertCustomer(request, response);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(CustomerServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case "/read":
                try {
                    checkCustomer(request, response);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(CustomerServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            default:
                showNewForm(request, response);
                
                break;
        }
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<HashMap<String, String>> feedback = favoirteDao.selectFeedback();
            
            for (HashMap<String, String> feedbackEntry : feedback) {
                String item = feedbackEntry.get("item");
                if (item != null) {
                    
                    if (item.contains("=")) {
                        item = item.substring(0, item.indexOf("=")).trim();
                    }
                    
                    
                    if (item.startsWith("{") || item.endsWith("}")) {
                        item = item.substring(1, item.length()).trim();
                    }
                    
                    // Update the item with cleaned data
                    feedbackEntry.put("item", item);
                }
            }
            
            System.out.println(feedback);
            request.setAttribute("feedback", feedback);
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CustomerServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Insert a customer 
    private void insertCustomer(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ClassNotFoundException {

        String name = request.getParameter("fullName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        Date dob = Date.valueOf(request.getParameter("dob"));
        int points = 0;

        Customer customer = new Customer(name, email, password, phone, address, dob, points);
        customerDao.addCustomer(customer);
        response.sendRedirect("signup.jsp");
    }
    
    // Check for user in the DB
    private void checkCustomer(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException, ClassNotFoundException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        boolean userExists = customerDao.checkCustomer(email, password);
        HttpSession session = request.getSession();
        if (userExists) {
            Customer customer = customerDao.selectCustomer(email, password);
            int points = customer.getPoints();
            String name = customer.getName();
            String phone = customer.getPhone();
            String address = customer.getAddress();
            Date dob = customer.getDob();
            session.setAttribute("status", "logged");
            session.setAttribute("userEmail", email);
            session.setAttribute("points", points);
            
            session.setAttribute("name", name);
            session.setAttribute("phone", phone);
            session.setAttribute("address", address);
            session.setAttribute("pass", password);
            session.setAttribute("dob", dob);
            
            
            
            if ("admin@123".equals(email)) {
                // If the user is admin redirect to admin dashboard
                System.out.println(email);
                response.sendRedirect("admin");
            } else {
                List<HashMap<String, String>> feedback = favoirteDao.selectFeedback();
                
               // Insertion Sort Algorithm
               int n = feedback.size();
               for (int i = 1; i < n; i++) {
                   HashMap<String, String> key = feedback.get(i);  
                   int keyRating = Integer.parseInt(key.get("rating"));
                   int j = i - 1;

                   // Move elements that are smaller than key's rating one position ahead
                   while (j >= 0 && Integer.parseInt(feedback.get(j).get("rating")) < keyRating) {
                       feedback.set(j + 1, feedback.get(j));
                       j--;
                   }

                   // Insert the key HashMap into its correct position
                   feedback.set(j + 1, key);
               }

               // Covert Item to a user firendly format
               for (HashMap<String, String> feedbackEntry : feedback) {
                   String item = feedbackEntry.get("item");
                   if (item != null) {
                       // Clean up the item string if it contains "=" or starts/ends with "{}"
                       if (item.contains("=")) {
                           item = item.substring(0, item.indexOf("=")).trim();
                       }
                       if (item.startsWith("{") || item.endsWith("}")) {
                           item = item.substring(1, item.length()).trim();
                       }
                       feedbackEntry.put("item", item);
                   }
               }
            System.out.println(feedback);
            request.setAttribute("feedback", feedback);
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
            }
        } else {
            request.setAttribute("alertMessage", "Invalid email or password.");
            request.setAttribute("alertType", "error");
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "CustomerServlet handles the customer sign-in, registration, and session management.";
    }
}
