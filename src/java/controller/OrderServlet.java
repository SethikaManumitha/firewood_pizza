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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import model.Feedback;
import model.Builder.*;
import dao.*;
import java.util.HashMap;
import model.decorator.*;
import model.stratergy.*;
import model.command.*;
@WebServlet("/order")
public class OrderServlet extends HttpServlet {

   
    private CustomPizzaDao customPizzaDao;
    private OrderDao orderDao;
    private FeedbackDao feedbackDao;

    @Override
    public void init() {
        customPizzaDao = new CustomPizzaDao();
        orderDao = new OrderDao();
        feedbackDao = new FeedbackDao();
        
        
    
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
            }else if ("ProcessPayment".equals(formAction)) {
                ProcessPayment(request, response);
            }else if ("GiveFeedback".equals(formAction)) {
                giveFeedback(request, response);
            }else if ("DeletePizza".equals(formAction)) {
                deleteFromCart(request, response);
            } else if ("UpdatePizza".equals(formAction)) {
                updateCart(request, response);
            }else if ("AddToCart".equals(formAction)) {
                addToCart(request, response);
            }else {
                showOrderForm(request, response);
            }
        } catch (Exception ex) {
            Logger.getLogger(OrderServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("errorMessage", "An error occurred: " + ex.getMessage());
            showOrderForm(request, response);
        }
    }

    // Redirect to order page
     private void showOrderForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String deliveryOption = request.getParameter("deliveryOption");
            
            String email = request.getParameter("email");
            
            //Retrieve all the unfinished orders
            List<Pizza> pizzas = customPizzaDao.selectAllPizza(email);
            
            //Retrieve all the favourite pizzas
            List<Pizza> favpizzas = orderDao.selectFavPizza(email);
            
            request.setAttribute("option", deliveryOption);
            request.setAttribute("pizzas", pizzas);
            
            request.setAttribute("favpizzas", favpizzas);
            RequestDispatcher dispatcher = request.getRequestDispatcher("order.jsp");
            dispatcher.forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(OrderServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

     
    // Update cart by adding favourite
    private void updateCart(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String name = request.getParameter("namefav");
            String email = request.getParameter("emailfav");
            
            // Update cart 
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
            showOrderForm(request, response);
        }
    }
    
    // Function to add to cart
     private void addToCart(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            
            String name = request.getParameter("nameadd");
            String email = request.getParameter("emailadd");
            
            // Update item status to 0 to add to cart
            orderDao.updatePizza(name, email);
        
            // Retrive the cart again 
            List<Pizza> pizzas = customPizzaDao.selectAllPizza(email);
            
            // Retrive the favorite pizza again
            List<Pizza> favpizzas = orderDao.selectFavPizza(email);
            
            request.setAttribute("pizzas", pizzas);
            request.setAttribute("favpizzas", favpizzas);
            RequestDispatcher dispatcher = request.getRequestDispatcher("order.jsp");
            dispatcher.forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(BuilderServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("errorMessage", "Failed to delete pizza: " + ex.getMessage());
            showOrderForm(request, response);
        }
    }
     
     // Remove a pizza from a cart
     private void deleteFromCart(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String name = request.getParameter("namedelete");
            String email = request.getParameter("emaildelete");

            // Delete pizza from cart
            orderDao.removePizza(name, email);

            // Retrieve pizza list again
            List<Pizza> pizzas = customPizzaDao.selectAllPizza(email);
            request.setAttribute("pizzas", pizzas);
            
            //Retrieve favourite pizza again
            List<Pizza> favpizzas = orderDao.selectFavPizza(email);
            System.out.println(favpizzas);
            
            request.setAttribute("favpizzas", favpizzas);
            RequestDispatcher dispatcher = request.getRequestDispatcher("order.jsp");
            dispatcher.forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(BuilderServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("errorMessage", "Failed to delete pizza: " + ex.getMessage());
            showOrderForm(request, response);
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
            showOrderForm(request, response);
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
            showOrderForm(request, response);
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
            showOrderForm(request, response);
        } catch (Exception ex) {
            Logger.getLogger(BuilderServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("errorMessage", ex.getMessage());
            showOrderForm(request, response);
        }
    }

    private void ProcessPayment(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
     
        String date = request.getParameter("date");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedDate = null;

        try {      
            parsedDate = dateFormat.parse(date); 
        } catch (ParseException e) {
            e.printStackTrace(); 
        }

        String address = request.getParameter("address");
        String includePack = request.getParameter("includePack");
        String optionOrder = request.getParameter("deliveryOption");
        String netTotalStr = request.getParameter("nettotal").replace("LKR.", "");
        Float totalAmount = Float.parseFloat(netTotalStr);
        String email = request.getParameter("emailOrder");
        List<Pizza> pizzas = customPizzaDao.selectAllPizza(email);

        HashMap<String, String> pizzaMap = new HashMap<>();
        for (Pizza pizza : pizzas) {
            pizzaMap.put(pizza.getName(), email);
        }

        request.setAttribute("pizzas", pizzas);

        int loyaltyPoints = orderDao.selectPoints(email); 
        String paymentMethod = request.getParameter("paymentMethod");

        // Stratergy Pattern
        PaymentContext paymentContext = new PaymentContext();

        switch (paymentMethod) {
        case "creditCard":
            String cardNumber = request.getParameter("cardNumber");
            paymentContext.setPaymentStrategy(new CreditCardPayment(cardNumber));
            break;

        case "digitalWallet":
            String walletId = request.getParameter("walletId");
            paymentContext.setPaymentStrategy(new DigitalWalletPayment(walletId));
            break;

        case "loyaltyProgram":
            paymentContext.setPaymentStrategy(new LoyaltyProgramPayment(loyaltyPoints));
            break;

        default:
            System.out.println("Invalid payment method selected.");
        }

        try {
            // Stratergy pattern
            paymentContext.pay(totalAmount);
            double discount = paymentContext.getDiscount();
            
            // Order Builder Pattern
            Order order = new OrderBuilder(0, "", email)
            .items(pizzaMap)
            .address(address)
            .deliveryOption(optionOrder)
            .paymentType(paymentMethod)
            .total(totalAmount)
            .discount(discount)
            .date(parsedDate)
            .build();
            
            // Command Pattern
            CommandInvoker invoker = new CommandInvoker();
            Command placeOrderCommand = new PlaceOrder(order);
            
            invoker.setCommand(placeOrderCommand);
            invoker.executeCommand();
            
           
            // Decorator pattern
            if("yes".equals(includePack)){ 
                Order wrappedOrder = new CustomPackage(order);
                System.out.println(wrappedOrder.getStatus());
                orderDao.insertOrder(wrappedOrder, email);
            }else{
                System.out.println(order.getStatus());
                orderDao.insertOrder(order, email);
            }
           
            
            
            if ("loyaltyProgram".equals(paymentMethod)) {
                loyaltyPoints += (int) (totalAmount / 10); 
                orderDao.updatePoints(loyaltyPoints, email);
            }
            
            String orderName = order.getItems().toString();
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("feedback.jsp");
            request.setAttribute("orderName", orderName);
            dispatcher.forward(request, response);

        } catch (IllegalStateException e) {
            response.getWriter().write("Payment failed: " + e.getMessage());
            System.out.println("Payment failed");
        }
}
    
    private void giveFeedback(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String orderid = request.getParameter("id");
            String user = request.getParameter("user");
            String feedback = request.getParameter("feedback");
            String rating = request.getParameter("rating");
    
            Feedback existingFeedback = feedbackDao.selectFeedback(orderid, user);
            Feedback feedbackObj = new Feedback(orderid,feedback,rating,user);
            
            // Command Pattern
            CommandInvoker invoker = new CommandInvoker();
            
            // Set feedback command
            invoker.setCommand(new FeedbackCommand(feedbackObj));
            
            // Invoke feedback command
            invoker.executeCommand(); 
            
            if (existingFeedback != null) {
                // If the user submits and a feedback already exist undo it.
                invoker.undoCommand();
                System.out.println(feedbackObj.getStatus());
                System.out.println(feedbackObj.getId());
                feedbackDao.updateFeedback(feedbackObj.getStatus(),feedbackObj.getId());
                request.setAttribute("btnStatus", "yes");
            } else { 
                //Insert feedback
                feedbackDao.insertFeedback(feedbackObj);   
                request.setAttribute("orderName", orderid);
                request.setAttribute("feedback", feedback);
                request.setAttribute("rating", rating);
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher("feedback.jsp");
            dispatcher.forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(OrderServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("errorMessage", ex.getMessage());
            showOrderForm(request, response);
        }
    }
    @Override
    public String getServletInfo() {
        return "Builder Servlet for Pizza Application";
    }
}
