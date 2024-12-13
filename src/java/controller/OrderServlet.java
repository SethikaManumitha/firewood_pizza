package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import java.util.List;
import javax.servlet.RequestDispatcher;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Builder.*;
import dao.*;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {

   
    private CustomPizzaDao customPizzaDao;
    private OrderDao orderDao;
 

    @Override
    public void init() {
   
        orderDao = new OrderDao();
    
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String formAction = request.getParameter("submit");

        try {
            if ("GetPizza".equals(formAction)) {
                buildPizza(request, response);
            }else {
                showBuilderForm(request, response);
            }
        } catch (Exception ex) {
            Logger.getLogger(OrderServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("errorMessage", "An error occurred: " + ex.getMessage());
            showBuilderForm(request, response);
        }
    }

    private void showBuilderForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String email = request.getParameter("email");
            List<Pizza> favpizzas = orderDao.selectFavPizza(email);
            System.out.println(favpizzas);
            request.setAttribute("favpizzas", favpizzas);
            RequestDispatcher dispatcher = request.getRequestDispatcher("order.jsp");
            dispatcher.forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(OrderServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void buildPizza(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String email = request.getParameter("emailOrder");
            List<Pizza> pizzas = orderDao.selectAllPizza(email);
            request.setAttribute("pizzas", pizzas);
            System.out.println(pizzas);
            
            List<Pizza> favpizzas = orderDao.selectFavPizza(email);
            System.out.println(favpizzas);
            request.setAttribute("favpizzas", favpizzas);
            RequestDispatcher dispatcher = request.getRequestDispatcher("order.jsp");
            dispatcher.forward(request, response);
            //showBuilderForm(request, response);
        } catch (Exception ex) {
            Logger.getLogger(OrderServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("errorMessage", ex.getMessage());
            showBuilderForm(request, response);
        }
    }

   
    @Override
    public String getServletInfo() {
        return "Builder Servlet for Pizza Application";
    }
}
