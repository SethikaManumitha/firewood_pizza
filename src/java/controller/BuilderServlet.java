/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;


import java.util.List;
import javax.servlet.RequestDispatcher;

import model.Crust;
import dao.CrustDao;

import model.Sauce;
import dao.SauceDao;

import model.Topping;
import dao.ToppingDao;
import model.Pizza;

import dao.CustomPizzaDao;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author MAS
 */
@WebServlet("/build")
public class BuilderServlet extends HttpServlet {
    
    private CrustDao crustDao;
    private SauceDao sauceDao;
    private ToppingDao toppingDao;
    private CustomPizzaDao customPizzaDao;
    
    public void init() {
        crustDao = new CrustDao();
        sauceDao = new SauceDao();
        toppingDao = new ToppingDao();
        customPizzaDao = new CustomPizzaDao();
    }
    
     @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doGet(request, response);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String formAction = request.getParameter("submit");

        if (formAction != null && formAction.equals("BuildPizza")) {
            buildPizza(request, response);
        } else {
            showBuilderForm(request, response);
        }
    }


   
   private void showBuilderForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
                List<Crust> crusts = crustDao.selectAllCrust();
                request.setAttribute("crusts", crusts);
                
                List<Sauce> sauces = sauceDao.selectAllSauce();
                request.setAttribute("sauces", sauces);
                
                 List<Topping> toppings = toppingDao.selectAllToppings();
                request.setAttribute("toppings", toppings);
                
		RequestDispatcher dispatcher = request.getRequestDispatcher("buildpizza.jsp");
		dispatcher.forward(request, response);
                
                
                
    }
   
    private void buildPizza(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        try {
            String name = request.getParameter("txtname");
            String email = request.getParameter("email");
            String crust = request.getParameter("crust");
            String sauce = request.getParameter("sauce");
            String[] toppingsArray = request.getParameterValues("topping");
            String size = request.getParameter("size");
            int price = Integer.parseInt(request.getParameter("totalAmountField"));
            boolean isCheeseIncluded = request.getParameter("includeCheese") != null;
            boolean isFavourite = request.getParameter("isFavourite") != null;
            int quantity = Integer.parseInt(request.getParameter("txtqty"));
            
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
                    .build();
            
            
            customPizzaDao.insertPizza(pizza, email);
            RequestDispatcher dispatcher = request.getRequestDispatcher("buildpizza.jsp");
	    dispatcher.forward(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BuilderServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
}

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
