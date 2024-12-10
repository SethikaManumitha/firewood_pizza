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


import dao.CustomerDao;
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import model.Customer;
/**
 *
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
            {
                try {
                    insertCustomer(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(CustomerServlet.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(CustomerServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                break;
            case "/read":   
            {
                try {
                    checkCustomer(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(CustomerServlet.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(CustomerServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

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

    private void checkCustomer(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, IOException, ServletException, ClassNotFoundException {

            String email = request.getParameter("email");
            String password = request.getParameter("password");

            boolean userExists = customerDao.selectCustomer(email, password);
            HttpSession session = request.getSession();
            if (userExists) {
                session.setAttribute("status", "logged");
                session.setAttribute("userEmail", email); 
                RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
                dispatcher.forward(request, response);
            } else {
                request.setAttribute("alertMessage", "Invalid email or password.");
                request.setAttribute("alertType", "error");
                RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
                dispatcher.forward(request, response);
            }

            
        }

 
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
