package controller;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Builder.Order;
import dao.AdminDao;
import model.state.*;  

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {

    private final AdminDao adminDao = new AdminDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String formAction = request.getParameter("submit");
        String state = request.getParameter("currentState"); // Optional: Set the initial state to "Placed"
        OrderContext orderContext = new OrderContext();

        try {    
            if ("ChangeState".equals(formAction)) {
                changeState(request, response, orderContext);
            }
            selectState(request, response, orderContext, state);
        } catch (Exception ex) {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, "Error in doGet", ex);
            request.setAttribute("errorMessage", "Unable to load admin data: " + ex.getMessage());
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private void selectState(HttpServletRequest request, HttpServletResponse response, OrderContext orderContext, String state)
            throws ServletException, IOException {

        // Load the orders for each state
        orderContext.setState(new PlacedState());
        List<Order> placedList = adminDao.selectAllOrders(orderContext.getStatus());

        orderContext.setState(new InPreparationState());
        List<Order> inPreparationList = adminDao.selectAllOrders(orderContext.getStatus());

        orderContext.setState(new OutForDeliveryState());
        List<Order> outForOrderList = adminDao.selectAllOrders(orderContext.getStatus());

        System.out.println("Placed:" + placedList);
        System.out.println("InPrep:" +inPreparationList);
        System.out.println("Out For Order:" +outForOrderList);
        
        if (state == null || state.isEmpty()) {
            state = "Placed";
        }

        System.out.println(state);
        switch (state) {
            case "InPreparation":
                request.setAttribute("orders", inPreparationList);
                RequestDispatcher prepDispatcher = request.getRequestDispatcher("Prepadmin.jsp");
                prepDispatcher.forward(request, response);
                break;

            case "OutForDelivery":
                request.setAttribute("orders", outForOrderList);
                RequestDispatcher outDispatcher = request.getRequestDispatcher("Outadmin.jsp");
                outDispatcher.forward(request, response);
                break;

            default:
                request.setAttribute("orders", placedList);
                RequestDispatcher placedDispatcher = request.getRequestDispatcher("placedadmin.jsp");
                placedDispatcher.forward(request, response);
                
                break;
        }
        System.out.println(state);
    }

    private void changeState(HttpServletRequest request, HttpServletResponse response, OrderContext orderContext)
            throws ServletException, IOException {
        try {
            String currentState = request.getParameter("currentState");
            String deliveryOption = request.getParameter("txtid");

            if (deliveryOption == null || deliveryOption.isEmpty()) {
                Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, "Delivery option ID is missing");
                request.setAttribute("errorMessage", "Delivery option ID is required.");
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }

            int orderId;
            try {
                orderId = Integer.parseInt(deliveryOption);
            } catch (NumberFormatException e) {
                Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, "Invalid delivery option ID: " + deliveryOption, e);
                request.setAttribute("errorMessage", "Invalid ID: Please provide a valid number.");
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }

            
            switch (currentState) {
                case "Placed":
                    orderContext.setState(new PlacedState());
                    break;
                case "InPreparation":
                    orderContext.setState(new InPreparationState());
                    break;
                case "OutForDelivery":
                    orderContext.setState(new OutForDeliveryState());
                    break;
                default:
                    throw new ServletException("Invalid state: " + currentState);
            }

            
            orderContext.processOrder();
            adminDao.updatePizza(orderId, orderContext.getStatus());

            selectState(request, response, orderContext, orderContext.getStatus());

        } catch (Exception ex) {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, "Error in changeState", ex);
            request.setAttribute("errorMessage", "Unable to process order state change: " + ex.getMessage());
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
