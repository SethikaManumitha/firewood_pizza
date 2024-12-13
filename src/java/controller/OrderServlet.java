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
        customPizzaDao = new CustomPizzaDao();
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
                getPizza(request, response);
            }else if ("BuildOrder".equals(formAction)) {
                buildOrder(request, response);
            }else if ("DeletePizza".equals(formAction)) {
                deletePizza(request, response);
            } else if ("UpdatePizza".equals(formAction)) {
                updatePizza(request, response);
            }else if ("GetFavPizza".equals(formAction)) {
                buildOrder(request, response);
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
            List<Pizza> pizzas = customPizzaDao.selectAllPizza(email);
            request.setAttribute("pizzas", pizzas);
            List<Pizza> favpizzas = orderDao.selectFavPizza(email);
            System.out.println(favpizzas);
            request.setAttribute("favpizzas", favpizzas);
            RequestDispatcher dispatcher = request.getRequestDispatcher("order.jsp");
            dispatcher.forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(OrderServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

     private void deletePizza(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String name = request.getParameter("namedelete");
            String email = request.getParameter("emaildelete");

            // Delete pizza from database
            customPizzaDao.deletePizza(name, email);

            // Fetch updated pizza list and display
            List<Pizza> pizzas = customPizzaDao.selectAllPizza(email);
            request.setAttribute("pizzas", pizzas);
            List<Pizza> favpizzas = orderDao.selectFavPizza(email);
            System.out.println(favpizzas);
            request.setAttribute("favpizzas", favpizzas);
            RequestDispatcher dispatcher = request.getRequestDispatcher("order.jsp");
            dispatcher.forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(BuilderServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("errorMessage", "Failed to delete pizza: " + ex.getMessage());
            showBuilderForm(request, response);
        }
    }
    
    private void updatePizza(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String name = request.getParameter("namefav");
            String email = request.getParameter("emailfav");

            // Delete pizza from database
            customPizzaDao.updatePizza(name, email);

            // Fetch updated pizza list and display
            List<Pizza> pizzas = customPizzaDao.selectAllPizza(email);
            request.setAttribute("pizzas", pizzas);
            List<Pizza> favpizzas = orderDao.selectFavPizza(email);
            System.out.println(favpizzas);
            request.setAttribute("favpizzas", favpizzas);
            RequestDispatcher dispatcher = request.getRequestDispatcher("order.jsp");
            dispatcher.forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(BuilderServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("errorMessage", "Failed to delete pizza: " + ex.getMessage());
            showBuilderForm(request, response);
        }
    }
    
    private void getPizza(HttpServletRequest request, HttpServletResponse response)
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
    
    private void getFavPizza(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String email = request.getParameter("favPizzaObj");
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
    
    private void buildOrder(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String name = request.getParameter("txtname");
            String email = request.getParameter("email");
            String crust = request.getParameter("crust");
            String sauce = request.getParameter("sauce");
            String[] toppingsArray = request.getParameterValues("topping");
            String size = request.getParameter("size");
            boolean isCheeseIncluded = request.getParameter("includeCheese") != null;
            boolean isFavourite = request.getParameter("isFavourite") != null;
            float price = Float.parseFloat(request.getParameter("totalAmountField")); 
            int qty = Integer.parseInt(request.getParameter("txtqty")); 
            // Build the pizza object
            Pizza pizza = new Pizza.Builder()
                    .setName(name)
                    .setCrust(crust)
                    .setSauce(sauce)
                    .setSize(size)
                    .addToppings(toppingsArray)
                    .includeCheese(isCheeseIncluded)
                    .setIsFavourite(isFavourite)
                    .setPrice(price)
                    .setQty(qty)
                    .build();

            
            

            
            customPizzaDao.insertPizza(pizza, email);

            
            List<Pizza> pizzas = customPizzaDao.selectAllPizza(email);
            request.setAttribute("pizzas", pizzas);
            showBuilderForm(request, response);
        } catch (Exception ex) {
            Logger.getLogger(BuilderServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("errorMessage", ex.getMessage());
            showBuilderForm(request, response);
        }
    }

   
    @Override
    public String getServletInfo() {
        return "Builder Servlet for Pizza Application";
    }
}
