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
import model.handler.*;

@WebServlet("/build")
public class BuilderServlet extends HttpServlet {

    private CrustDao crustDao;
    private SauceDao sauceDao;
    private ToppingDao toppingDao;
    private CustomPizzaDao customPizzaDao;

    private final PizzaHandler sizeHandler = new SizeHandler();
    private final PizzaHandler crustHandler = new CrustHandler();
    private final PizzaHandler sauceHandler = new SauceHandler();
    private final PizzaHandler toppingHandler = new ToppingHandler();

    @Override
    public void init() {
        crustDao = new CrustDao();
        sauceDao = new SauceDao();
        toppingDao = new ToppingDao();
        customPizzaDao = new CustomPizzaDao();

        // Chain of handlers
        sizeHandler.setNextHandler(crustHandler);
        crustHandler.setNextHandler(sauceHandler);
        sauceHandler.setNextHandler(toppingHandler);
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
            if ("BuildPizza".equals(formAction)) {
                buildPizza(request, response);
            } else if ("DeletePizza".equals(formAction)) {
                deletePizza(request, response);
            } else if ("UpdatePizza".equals(formAction)) {
                updatePizza(request, response);
            } else {
                showBuilderForm(request, response);
            }
        } catch (Exception ex) {
            Logger.getLogger(BuilderServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("errorMessage", "An error occurred: " + ex.getMessage());
            showBuilderForm(request, response);
        }
    }

    private void showBuilderForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Crust> crusts = crustDao.selectAllCrust();
            request.setAttribute("crusts", crusts);

            List<Sauce> sauces = sauceDao.selectAllSauce();
            request.setAttribute("sauces", sauces);

            List<Topping> toppings = toppingDao.selectAllToppings();
            request.setAttribute("toppings", toppings);
            
            String email = request.getParameter("email");
            List<Pizza> pizzas = customPizzaDao.selectAllPizza(email);
            request.setAttribute("pizzas", pizzas);
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("buildpizza.jsp");
            dispatcher.forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(BuilderServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("errorMessage", "Failed to load form data: " + ex.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("buildpizza.jsp");
            dispatcher.forward(request, response);
        }
    }

    // Function to build pizza
    private void buildPizza(HttpServletRequest request, HttpServletResponse response)
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
            int qty = 1; 
            
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

            sizeHandler.handle(pizza);
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
            RequestDispatcher dispatcher = request.getRequestDispatcher("buildpizza.jsp");
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
            RequestDispatcher dispatcher = request.getRequestDispatcher("buildpizza.jsp");
            dispatcher.forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(BuilderServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("errorMessage", "Failed to delete pizza: " + ex.getMessage());
            showBuilderForm(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Builder Servlet for Pizza Application";
    }
}
