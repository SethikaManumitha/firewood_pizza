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
import model.Customer;

/**
 * @author MAS
 */
@WebServlet("/")
public class CustomerServlet extends HttpServlet {

    private CustomerDao customerDao;

    public void init() {
        customerDao = new CustomerDao();
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
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
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
            session.setAttribute("status", "logged");
            session.setAttribute("userEmail", email);
            session.setAttribute("points", points);

            if ("admin@123".equals(email)) {
                // If the user is admin redirect to admin dashboard
                response.sendRedirect(request.getContextPath() + "/admin");
            } else {
                // Redirect customer interface
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
